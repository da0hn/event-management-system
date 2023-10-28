package br.com.event.management.system.core.common.domain;

import br.com.event.management.system.core.events.domain.integration.event.PartnerCreatedIntegrationEvent;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.EXISTING_PROPERTY,
  property = "eventName"
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = PartnerCreatedIntegrationEvent.class, name = "PartnerCreatedIntegrationEvent")
})
public abstract class IntegrationEvent<T> {

  private final String eventName;

  private final T payload;

  private final LocalDateTime occurredOn;

  private final Long version;

}
