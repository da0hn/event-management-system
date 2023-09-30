package br.com.event.management.system.events.application.service.impl;

import br.com.event.management.system.events.application.dto.RegisterCustomerInput;
import br.com.event.management.system.events.application.service.CustomerService;
import br.com.event.management.system.events.domain.commands.CreateCustomerCommand;
import br.com.event.management.system.events.domain.entities.Customer;
import br.com.event.management.system.events.domain.repositories.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ActiveProfiles("test-containers")
@DisplayName("Customer Application Service Tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CustomerServiceImplTest {

  @Autowired
  private CustomerService sut;

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  @DisplayName("Should list all customers")
  void test1() {
    this.customerRepository.add(Customer.create(new CreateCustomerCommand("Customer 1", "13088528009")));
    this.customerRepository.add(Customer.create(new CreateCustomerCommand("Customer 2", "57855910069")));
    this.customerRepository.add(Customer.create(new CreateCustomerCommand("Customer 3", "53643102054")));

    final var response = this.sut.findAll();

    Assertions.assertThat(response).hasSize(3);
  }

  @Test
  @DisplayName("Should register a new customer")
  void test2() {
    final var customer = this.sut.register(new RegisterCustomerInput("Customer 4", "02281761070"));

    Assertions.assertThat(customer.getId()).isNotNull();
    Assertions.assertThat(customer.getName()).isEqualTo("Customer 4");
    Assertions.assertThat(customer.getCpf()).isNotNull();
    Assertions.assertThat(customer.getCpf().value()).isEqualTo("02281761070");

    final var list = this.sut.findAll();

    Assertions.assertThat(list).hasSize(1);
  }

}
