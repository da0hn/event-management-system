package br.com.event.management.system.common.domain.valueobjects;

import br.com.event.management.system.common.domain.EntityId;

import java.util.UUID;

public record PartnerId(UUID value) implements EntityId<UUID> {

  public static PartnerId newInstance() {
    return new PartnerId(UUID.randomUUID());
  }

  public static PartnerId of(final UUID value) {
    return new PartnerId(value);
  }

  public static PartnerId of(final String value) {
    return new PartnerId(UUID.fromString(value));
  }

}
