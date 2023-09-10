package br.com.event.management.system.common.domain.valueobjects;

import br.com.event.management.system.common.domain.EntityId;

import java.util.UUID;

public record EventId(UUID value) implements EntityId<UUID> {

  public static EventId newInstance() {
    return new EventId(UUID.randomUUID());
  }

  public static EventId of(final UUID value) {
    return new EventId(value);
  }

  public static EventId of(final String value) {
    return new EventId(UUID.fromString(value));
  }

}
