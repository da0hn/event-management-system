package br.com.event.management.system.core.events.domain.integration.event;

import br.com.event.management.system.core.common.domain.IntegrationEvent;
import br.com.event.management.system.core.events.domain.events.PartnerCreated;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.UUID;

public class PartnerCreatedIntegrationEvent extends IntegrationEvent<PartnerCreatedIntegrationEvent.Payload> {

  @JsonCreator
  public PartnerCreatedIntegrationEvent(
    final Payload payload,
    final LocalDateTime occurredOn,
    final Long version
  ) {
    super(PartnerCreatedIntegrationEvent.class.getSimpleName(), payload, occurredOn, version);
  }

  public static PartnerCreatedIntegrationEvent of(final PartnerCreated event) {
    return new PartnerCreatedIntegrationEvent(
      new Payload(event.getAggregateId().value(), event.getName()),
      event.getOccurredOn(),
      event.getVersion()
    );
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, IntegrationEvent.class);
  }

  public record Payload(UUID id, String name) {

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

  }

}
