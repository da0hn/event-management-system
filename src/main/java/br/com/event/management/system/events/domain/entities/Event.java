package br.com.event.management.system.events.domain.entities;

import br.com.event.management.system.common.domain.AggregateRoot;
import br.com.event.management.system.common.domain.valueobjects.EventId;
import br.com.event.management.system.common.domain.valueobjects.PartnerId;
import br.com.event.management.system.events.domain.commands.CreateEventCommand;
import br.com.event.management.system.events.domain.commands.CreateEventSectionCommand;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
public class Event extends AggregateRoot<EventId> {

  private final Long totalSpotsReserved;

  private final PartnerId partnerId;

  private final Set<EventSection> sections = new HashSet<>(0);

  private boolean published;

  private String name;

  private Optional<String> description;

  private LocalDate date;

  private Long totalSpots;

  public Event(
    final EventId id,
    final String name,
    final Optional<String> description,
    final LocalDate date,
    final boolean published,
    final Long totalSpots,
    final Long totalSpotsReserved,
    final PartnerId partnerId,
    final Collection<EventSection> sections
  ) {
    super(id);
    this.name = name;
    this.description = description;
    this.date = date;
    this.published = published;
    this.totalSpots = totalSpots;
    this.totalSpotsReserved = totalSpotsReserved;
    this.partnerId = partnerId;
    this.sections.addAll(sections);
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
      command.partnerId(),
      new HashSet<>()
    );
  }

  public void addSection(final CreateEventSectionCommand command) {
    final var section = EventSection.create(command);
    this.sections.add(section);
    this.totalSpots += section.getTotalSpots();
  }

  public void changeName(final String name) {
    this.name = name;
  }

  public void changeDescription(final String description) {
    this.description = Optional.ofNullable(description);
  }

  public void changeDate(final LocalDate date) {
    this.date = date;
  }

  public void publishAll() {
    this.publish();
    this.sections.forEach(EventSection::publishAll);
  }

  public void publish() {
    this.published = true;
  }

  public void unPublish() {
    this.published = false;
  }

  public Set<EventSection> getSections() {
    return Collections.unmodifiableSet(this.sections);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, AggregateRoot.class);
  }

}
