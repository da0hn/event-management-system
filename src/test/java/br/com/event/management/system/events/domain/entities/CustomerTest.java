package br.com.event.management.system.events.domain.entities;

import br.com.event.management.system.common.domain.valueobjects.Cpf;
import br.com.event.management.system.common.domain.valueobjects.CustomerId;
import br.com.event.management.system.events.domain.commands.CreateCustomerCommand;
import br.com.event.management.system.events.domain.entities.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
class CustomerTest {

  @Test
  @DisplayName("Should create customer")
  void test1() {

    final Consumer<Customer> nameNotEmptyOrBlank = customer -> assertThat(customer.getName()).isNotEmpty().isNotBlank();
    final Consumer<Customer> cpfNotEmptyOrBlank = customer -> assertThat(customer.getCpf().value()).isNotEmpty().isNotBlank();
    final Consumer<Customer> customerIdNotNull = customer -> assertThat(customer.getId()).isNotNull();

    final var customer = Customer.create(new CreateCustomerCommand("Gabriel", "792.268.310-33"));
    assertThat(customer)
      .satisfies(nameNotEmptyOrBlank, cpfNotEmptyOrBlank, customerIdNotNull);
  }

  @Test
  @DisplayName("Customer's with same id should be equal")
  void test2() {
    final var customerId = CustomerId.newInstance();

    final var customerA = new Customer(customerId, new Cpf("722.644.930-70"), "Gabriel A");
    final var customerB = new Customer(customerId, new Cpf("651.351.990-04"), "Gabriel B");

    final var isEqual = customerA.equals(customerB);
    Assertions.assertThat(isEqual).isTrue();
  }

  @Test
  @DisplayName("Customer's with different id should be different")
  void test3() {

    final var customerA = new Customer(CustomerId.newInstance(), new Cpf("722.644.930-70"), "Gabriel A");
    final var customerB = new Customer(CustomerId.newInstance(), new Cpf("651.351.990-04"), "Gabriel B");

    final var isEqual = customerA.equals(customerB);
    Assertions.assertThat(isEqual).isFalse();
  }

}
