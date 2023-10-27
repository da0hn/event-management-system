package br.com.event.management.system.core.events.domain.events;

import br.com.event.management.system.core.common.domain.DomainEvent;
import br.com.event.management.system.core.common.domain.valueobjects.PartnerId;
import br.com.event.management.system.core.events.domain.entities.Partner;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

@Getter
public class PartnerChangedName extends DomainEvent {

  private final String name;

  public PartnerChangedName(
    final PartnerId aggregateId,
    final LocalDateTime occurredOn,
    final String name
  ) {
    super(aggregateId, occurredOn, 1L);
    this.name = name;
  }

  public static PartnerChangedName of(final Partner partner) {
    return new PartnerChangedName(partner.getId(), LocalDateTime.now(), partner.getName());
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, true, DomainEvent.class);
  }

}
