package br.com.event.management.system.events.domain;

import br.com.event.management.system.common.domain.AggregateRoot;

import java.util.UUID;

public class Customer extends AggregateRoot {

  private final String id;

  private final String cpf;

  private final String name;

  public Customer(final String id, final String cpf, final String name) {
    this.id = id;
    this.cpf = cpf;
    this.name = name;
  }

  public static Customer create(final CreateCustomerCommand command) {
    return new Customer(UUID.randomUUID().toString(), command.cpf(), command.name());
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Customer{");
    sb.append("id='").append(this.id).append('\'');
    sb.append(", cpf='").append(this.cpf).append('\'');
    sb.append(", name='").append(this.name).append('\'');
    sb.append('}');
    return sb.toString();
  }

}
