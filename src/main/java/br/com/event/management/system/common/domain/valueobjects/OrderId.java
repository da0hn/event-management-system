package br.com.event.management.system.common.domain.valueobjects;

import br.com.event.management.system.common.domain.EntityId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.UUID;

public record OrderId(UUID value) implements EntityId<UUID> {

  public static OrderId newInstance() {
    return new OrderId(UUID.randomUUID());
  }

  public static OrderId of(final UUID value) {
    return new OrderId(value);
  }

  public static OrderId of(final String value) {
    return new OrderId(UUID.fromString(value));
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
