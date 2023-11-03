package br.com.event.management.system.core.stored.events.domain.entities;

import br.com.event.management.system.core.common.domain.AggregateRoot;
import br.com.event.management.system.core.common.domain.valueobjects.StoredEventId;
import br.com.event.management.system.core.stored.events.domain.commands.StoredEventCommand;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

public class StoredEvent extends AggregateRoot<StoredEventId> {

  private final String body;

  private final LocalDateTime occurredOn;

  private final String typeName;

  public StoredEvent(final StoredEventId id, final String body, final LocalDateTime occurredOn, final String typeName) {
    super(id);
    this.body = body;
    this.occurredOn = occurredOn;
    this.typeName = typeName;
  }

  public static StoredEvent create(final StoredEventCommand command) {
    final var storedEvent = new StoredEvent(
      StoredEventId.newInstance(),
      command.domainEventAsJson(),
      command.occurredOn(),
      command.domainEventClassName()
    );
    return storedEvent;
  }

  public String getBody() {
    return this.body;
  }

  public LocalDateTime getOccurredOn() {
    return this.occurredOn;
  }

  public String getTypeName() {
    return this.typeName;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, true, AggregateRoot.class);
  }

}
