package br.com.event.management.system.core.stored.events.domain.commands;

import br.com.event.management.system.core.common.domain.DomainEvent;
import br.com.event.management.system.core.stored.events.domain.entities.JsonSerializer;

import java.time.LocalDateTime;

public record StoredEventCommand(
  DomainEvent domainEvent,
  LocalDateTime occurredOn,
  JsonSerializer jsonSerializer
) {

  public String domainEventAsJson() {
    return this.jsonSerializer.toJson(this.domainEvent);
  }

  public String domainEventClassName() {
    return this.domainEvent.getClass().getSimpleName();
  }

}
