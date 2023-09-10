package br.com.event.management.system.events.domain;

import br.com.event.management.system.common.domain.AggregateRoot;
import br.com.event.management.system.common.domain.valueobjects.Cpf;
import br.com.event.management.system.common.domain.valueobjects.CustomerId;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public class Customer extends AggregateRoot<CustomerId> {

  private final Cpf cpf;

  private final String name;

  public Customer(final CustomerId id, final Cpf cpf, final String name) {
    super(id);
    this.cpf = cpf;
    this.name = name;
  }

  public static Customer create(final CreateCustomerCommand command) {
    return new Customer(CustomerId.newInstance(), new Cpf(command.cpf()), command.name());
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, AggregateRoot.class);
  }

}
