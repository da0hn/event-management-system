package br.com.event.management.system.common.domain.valueobjects;

import br.com.event.management.system.common.domain.EntityId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.UUID;

public record EventSpotId(UUID value) implements EntityId<UUID> {

  public static EventSpotId newInstance() {
    return new EventSpotId(UUID.randomUUID());
  }

  public static EventSpotId of(final UUID value) {
    return new EventSpotId(value);
  }

  public static EventSpotId of(final String value) {
    return new EventSpotId(UUID.fromString(value));
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
