package br.com.event.management.system.events.application.service;

import br.com.event.management.system.common.domain.valueobjects.CustomerId;
import br.com.event.management.system.events.application.dto.RegisterCustomerInput;
import br.com.event.management.system.events.application.dto.UpdateCustomerInput;
import br.com.event.management.system.events.domain.entities.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

  List<Customer> list();

  Customer register(RegisterCustomerInput input);

  Customer update(UUID customerId, UpdateCustomerInput input);

}
