package br.com.event.management.system.events.domain;

import br.com.event.management.system.common.domain.Entity;
import br.com.event.management.system.common.domain.valueobjects.EventSectionId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Optional;

public class EventSection extends Entity<EventSectionId> {

  private final String name;

  private final Optional<String> description;

  private final BigDecimal price;

  private final boolean published;

  private final Long totalSpots;

  private final Long totalSpotsReserved;

  public EventSection(
    final EventSectionId id,
    final String name,
    final Optional<String> description,
    final BigDecimal price,
    final boolean published,
    final Long totalSpots,
    final Long totalSpotsReserved
  ) {
    super(id);
    this.name = name;
    this.description = description;
    this.price = price;
    this.published = published;
    this.totalSpots = totalSpots;
    this.totalSpotsReserved = totalSpotsReserved;
  }

  public static EventSection create(final CreateEventSectionCommand command) {
    return new EventSection(
      EventSectionId.newInstance(),
      command.name(),
      Optional.ofNullable(command.description()),
      command.price(),
      false,
      command.totalSpots(),
      0L
    );
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, Entity.class);
  }

}
