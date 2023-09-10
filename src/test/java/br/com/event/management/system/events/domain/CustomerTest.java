package br.com.event.management.system.events.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
class CustomerTest {

  @Test
  @DisplayName("Should create customer")
  void test1() {

    final Consumer<Customer> validName = customer -> Assertions.assertThat(customer.getName()).isNotEmpty().isNotBlank();
    final Consumer<Customer> validCpf = customer -> Assertions.assertThat(customer.getCpf().value()).isNotEmpty().isNotBlank();

    final var customer = Customer.create(new CreateCustomerCommand("Gabriel", "792.268.310-33"));
    Assertions.assertThat(customer)
      .satisfies(validName, validCpf);
  }

}
