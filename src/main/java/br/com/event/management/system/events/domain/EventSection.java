package br.com.event.management.system.events.domain;

import br.com.event.management.system.common.domain.Entity;
import br.com.event.management.system.common.domain.valueobjects.EventSectionId;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Getter
public class EventSection extends Entity<EventSectionId> {

  private final Long totalSpots;

  private final Long totalSpotsReserved;

  private final Set<EventSpot> spots = new HashSet<>(0);

  private boolean published;

  private BigDecimal price;

  private String name;

  private Optional<String> description;

  public EventSection(
    final EventSectionId id,
    final String name,
    final Optional<String> description,
    final BigDecimal price,
    final boolean published,
    final Long totalSpots,
    final Long totalSpotsReserved,
    final Set<EventSpot> spots
  ) {
    super(id);
    this.name = name;
    this.description = description;
    this.price = price;
    this.published = published;
    this.totalSpots = totalSpots;
    this.totalSpotsReserved = totalSpotsReserved;
    this.spots.addAll(spots);
  }

  public static EventSection create(final CreateEventSectionCommand command) {
    final var section = new EventSection(
      EventSectionId.newInstance(),
      command.name(),
      Optional.ofNullable(command.description()),
      command.price(),
      false,
      command.totalSpots(),
      0L,
      new HashSet<>()
    );
    section.initializeSpots();
    return section;
  }

  public void changeName(final String name) {
    this.name = name;
  }

  public void changeDescription(final String description) {
    this.description = Optional.ofNullable(description);
  }

  public void changePrice(final BigDecimal price) {
    this.price = price;
  }

  public void publish() {
    this.published = true;
  }

  public void unPublish() {
    this.published = false;
  }

  public void publishAll() {
    this.publish();
    this.spots.forEach(EventSpot::publish);
  }

  public Set<EventSpot> getSpots() {
    return Collections.unmodifiableSet(this.spots);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, Entity.class);
  }

  private void initializeSpots() {
    final var spots = LongStream.range(0, this.totalSpots)
      .mapToObj(index -> EventSpot.create())
      .collect(Collectors.toUnmodifiableSet());
    this.spots.addAll(spots);
  }

}
