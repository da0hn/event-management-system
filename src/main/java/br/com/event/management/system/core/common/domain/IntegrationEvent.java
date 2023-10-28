package br.com.event.management.system.core.common.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@JsonTypeInfo(
  use = JsonTypeInfo.Id.CLASS,
  property = "type"
)
public abstract class IntegrationEvent<T> {

  private final String eventName;

  private final T payload;

  private final LocalDateTime occurredOn;

  private final Long version;

}
