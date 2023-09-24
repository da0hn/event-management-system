package br.com.event.management.system.common.domain.valueobjects;

import br.com.event.management.system.common.domain.EntityId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.UUID;

public record CustomerId(UUID value) implements EntityId<UUID> {

  public static CustomerId newInstance() {
    return new CustomerId(UUID.randomUUID());
  }

  public static CustomerId of(final UUID value) {
    return new CustomerId(value);
  }

  public static CustomerId of(final String value) {
    return new CustomerId(UUID.fromString(value));
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
