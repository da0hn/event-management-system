package br.com.event.management.system.events.application.service;

import br.com.event.management.system.events.application.dto.RegisterCustomerInput;
import br.com.event.management.system.events.domain.entities.Customer;

import java.util.List;

public interface CustomerService {

  List<Customer> list();

  Customer register(RegisterCustomerInput input);

}
