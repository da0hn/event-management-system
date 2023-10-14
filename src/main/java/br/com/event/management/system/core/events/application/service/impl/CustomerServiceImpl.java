package br.com.event.management.system.core.events.application.service.impl;

import br.com.event.management.system.infrastructure.UnitOfWork;
import br.com.event.management.system.core.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.core.common.domain.valueobjects.CustomerId;
import br.com.event.management.system.core.events.application.dto.RegisterCustomerInput;
import br.com.event.management.system.core.events.application.dto.UpdateCustomerInput;
import br.com.event.management.system.core.events.application.service.CustomerService;
import br.com.event.management.system.core.events.domain.commands.CreateCustomerCommand;
import br.com.event.management.system.core.events.domain.entities.Customer;
import br.com.event.management.system.core.events.domain.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  private final UnitOfWork unitOfWork;

  @Override
  public List<Customer> findAll() {
    return this.customerRepository.findAll();
  }

  @Override
  public Customer register(final RegisterCustomerInput input) {
    return this.unitOfWork.execute((transaction) -> {
      final var newCustomer = Customer.create(new CreateCustomerCommand(input.name(), input.cpf()));
      this.customerRepository.add(newCustomer);
      return newCustomer;
    });
  }

  @Override
  public Customer update(final UUID customerId, final UpdateCustomerInput input) {
    return this.unitOfWork.execute((transaction) -> {
      final var customer = this.findCustomerOrElseThrow(customerId);

      customer.changeName(input.name());

      this.customerRepository.add(customer);
      return customer;
    });
  }

  private Customer findCustomerOrElseThrow(final UUID customerId) {
    return this.customerRepository.findById(CustomerId.of(customerId))
      .orElseThrow(() -> new DomainEntityNotFoundException("Customer not found"));
  }

}
