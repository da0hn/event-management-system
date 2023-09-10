package br.com.event.management.system.events.domain;

import br.com.event.management.system.common.domain.AggregateRoot;
import br.com.event.management.system.common.domain.valueobjects.Cpf;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.UUID;

public class Customer extends AggregateRoot {

  private final String id;

  private final Cpf cpf;

  private final String name;

  public Customer(final String id, final Cpf cpf, final String name) {
    this.id = id;
    this.cpf = cpf;
    this.name = name;
  }

  public static Customer create(final CreateCustomerCommand command) {
    return new Customer(UUID.randomUUID().toString(), new Cpf(command.cpf()), command.name());
  }

  public Cpf getCpf() {
    return this.cpf;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
