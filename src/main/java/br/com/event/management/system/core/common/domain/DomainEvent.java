package br.com.event.management.system.core.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class DomainEvent {

  private final EntityId<UUID> aggregateId;

  private final LocalDateTime occurredOn;

  private final Long version;

}
