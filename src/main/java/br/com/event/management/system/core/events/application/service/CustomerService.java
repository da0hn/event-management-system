package br.com.event.management.system.core.events.application.service;

import br.com.event.management.system.core.events.application.dto.UpdateCustomerInput;
import br.com.event.management.system.core.events.domain.entities.Customer;
import br.com.event.management.system.core.events.application.dto.RegisterCustomerInput;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

  List<Customer> findAll();

  Customer register(RegisterCustomerInput input);

  Customer update(UUID customerId, UpdateCustomerInput input);

}
