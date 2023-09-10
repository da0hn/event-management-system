package br.com.event.management.system.common.domain.valueobjects;

import br.com.event.management.system.common.domain.EntityId;

import java.util.UUID;

public record EventSectionId(UUID value) implements EntityId<UUID> {

  public static EventSectionId newInstance() {
    return new EventSectionId(UUID.randomUUID());
  }

  public static EventSectionId of(final UUID value) {
    return new EventSectionId(value);
  }

  public static EventSectionId of(final String value) {
    return new EventSectionId(UUID.fromString(value));
  }

}
