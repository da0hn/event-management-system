package br.com.event.management.system.core.common.domain.valueobjects;

import br.com.event.management.system.core.common.domain.EntityId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.UUID;

public record StoredEventId(UUID value) implements EntityId<UUID> {

  public static StoredEventId newInstance() {
    return new StoredEventId(UUID.randomUUID());
  }

  public static StoredEventId of(final UUID value) {
    return new StoredEventId(value);
  }

  public static StoredEventId of(final String value) {
    return new StoredEventId(UUID.fromString(value));
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
