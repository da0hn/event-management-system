package br.com.event.management.system.events.domain;

import br.com.event.management.system.common.domain.AggregateRoot;
import br.com.event.management.system.common.domain.valueobjects.PartnerId;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public class Partner extends AggregateRoot<PartnerId> {

  private final String name;

  public Partner(final PartnerId id, final String name) {
    super(id);
    this.name = name;
  }

  public static Partner create(final CreatePartnerCommand command) {
    return new Partner(PartnerId.newInstance(), command.name());
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, AggregateRoot.class);
  }

}
