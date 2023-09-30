package br.com.event.management.system.events.application.service.impl;

import br.com.event.management.system.common.application.UnitOfWork;
import br.com.event.management.system.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.common.domain.valueobjects.CustomerId;
import br.com.event.management.system.events.application.dto.RegisterCustomerInput;
import br.com.event.management.system.events.application.dto.UpdateCustomerInput;
import br.com.event.management.system.events.application.service.CustomerService;
import br.com.event.management.system.events.domain.commands.CreateCustomerCommand;
import br.com.event.management.system.events.domain.entities.Customer;
import br.com.event.management.system.events.domain.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  private final UnitOfWork unitOfWork;

  @Override
  public List<Customer> list() {
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
  public Customer update(final CustomerId id, final UpdateCustomerInput input) {
    return this.unitOfWork.execute((transaction) -> {
      final var customer = this.customerRepository.findById(id)
        .orElseThrow(() -> new DomainEntityNotFoundException("Customer not found"));

      customer.changeName(input.name());

      this.customerRepository.add(customer);
      return customer;
    });
  }

}
