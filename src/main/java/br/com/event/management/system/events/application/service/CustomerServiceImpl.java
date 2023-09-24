package br.com.event.management.system.events.application.service;

import br.com.event.management.system.events.application.dto.RegisterCustomerInput;
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

  @Override
  public List<Customer> list() {
    return this.customerRepository.findAll();
  }

  @Override
  public Customer register(final RegisterCustomerInput input) {
    final var customer = Customer.create(new CreateCustomerCommand(input.name(), input.cpf()));
    this.customerRepository.add(customer);
    return customer;
  }

}
