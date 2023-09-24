package br.com.event.management.system.events.infrastructure.database;

import br.com.event.management.system.events.domain.CreateCustomerCommand;
import br.com.event.management.system.events.domain.Customer;
import br.com.event.management.system.events.infrastructure.database.repositories.impl.CustomerMysqlRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ActiveProfiles("test-containers")
@DisplayName("Customer Repository Tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CustomerMysqlRepositoryTest {

  @Autowired
  private CustomerMysqlRepository repository;

  @Test
  @DisplayName("Should execute all repository operations")
  void test1() {

    final var customer = Customer.create(new CreateCustomerCommand("Customer 1", "29793269090"));

    this.repository.add(customer);

    final var maybePartnerModel = this.repository.findById(customer.getId());

    Assertions.assertThat(maybePartnerModel).isPresent();
    Assertions.assertThat(maybePartnerModel.get().getName()).isEqualTo(customer.getName());
    Assertions.assertThat(maybePartnerModel.get().getId()).isEqualTo(customer.getId());

    customer.changeName("Customer 2");
    this.repository.add(customer);

    final var maybeUpdatedPartnerModel = this.repository.findById(customer.getId());

    Assertions.assertThat(maybeUpdatedPartnerModel).isPresent();
    Assertions.assertThat(maybeUpdatedPartnerModel.get().getName()).isEqualTo("Customer 2");
    Assertions.assertThat(maybeUpdatedPartnerModel.get().getId()).isEqualTo(customer.getId());

    final var allPartners = this.repository.findAll();
    Assertions.assertThat(allPartners).hasSize(1);

    this.repository.remove(customer);

    Assertions.assertThat(this.repository.findAll()).isEmpty();
  }

  @Test
  @DisplayName("Should not create an customer with existent CPF")
  void test2() {
    final var customer1 = Customer.create(new CreateCustomerCommand("Customer 1", "29793269090"));
    final var customer2 = Customer.create(new CreateCustomerCommand("Customer 2", "29793269090"));

    this.repository.add(customer1);

    Assertions.assertThatThrownBy(() -> this.repository.add(customer2))
      .isInstanceOf(RuntimeException.class)
      .hasMessageContaining("could not execute statement");

  }

}
