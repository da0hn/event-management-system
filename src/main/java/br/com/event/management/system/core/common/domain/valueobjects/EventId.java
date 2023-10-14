package br.com.event.management.system.core.common.domain.valueobjects;

import br.com.event.management.system.core.common.domain.EntityId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
