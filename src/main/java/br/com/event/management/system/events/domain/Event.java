package br.com.event.management.system.events.domain;

import br.com.event.management.system.common.domain.AggregateRoot;
import br.com.event.management.system.common.domain.valueobjects.EventId;
import br.com.event.management.system.common.domain.valueobjects.PartnerId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.Optional;

public class Event extends AggregateRoot<EventId> {

  private final String name;

  private final Optional<String> description;

  private final LocalDate date;

  private final boolean published;

  private final Long totalSpots;

  private final Long totalSpotsReserved;

  private final PartnerId partnerId;

  public Event(
    final EventId id,
    final String name,
    final Optional<String> description,
    final LocalDate date,
    final boolean published,
    final Long totalSpots,
    final Long totalSpotsReserved,
    final PartnerId partnerId
  ) {
    super(id);
    this.name = name;
    this.description = description;
    this.date = date;
    this.published = published;
    this.totalSpots = totalSpots;
    this.totalSpotsReserved = totalSpotsReserved;
    this.partnerId = partnerId;
  }

  public static Event create(final CreateEventCommand command) {
    return new Event(
      EventId.newInstance(),
      command.name(),
      Optional.ofNullable(command.description()),
      command.date(),
      false,
      0L,
      0L,
      command.partnerId()
    );
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, AggregateRoot.class);
  }

}
