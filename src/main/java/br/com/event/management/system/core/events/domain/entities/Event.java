package br.com.event.management.system.core.events.domain.entities;

import br.com.event.management.system.core.common.domain.AggregateRoot;
import br.com.event.management.system.core.common.domain.Entity;
import br.com.event.management.system.core.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.core.common.domain.valueobjects.EventId;
import br.com.event.management.system.core.common.domain.valueobjects.EventSectionId;
import br.com.event.management.system.core.common.domain.valueobjects.EventSpotId;
import br.com.event.management.system.core.common.domain.valueobjects.PartnerId;
import br.com.event.management.system.core.events.domain.commands.CreateEventCommand;
import br.com.event.management.system.core.events.domain.commands.CreateEventSectionCommand;
import br.com.event.management.system.core.events.domain.commands.UpdateSectionInformationCommand;
import br.com.event.management.system.core.events.domain.commands.UpdateSpotLocationCommand;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

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

  public void changeSectionInformation(final UpdateSectionInformationCommand updateSectionInformationCommand) {

    final var section = this.searchSectionOrElseThrow(item -> item.getId().equals(updateSectionInformationCommand.sectionId()));

    section.changeName(updateSectionInformationCommand.name());
    section.changeDescription(updateSectionInformationCommand.description());
  }

  public EventSection searchSectionOrElseThrow(final Predicate<? super EventSection> criteria) {
    return this.sections.stream()
      .filter(criteria)
      .findFirst()
      .orElseThrow(() -> new DomainEntityNotFoundException("Section not found"));
  }

  public boolean allowReserveSpot(final EventSectionId eventSectionId, final EventSpotId eventSpotId) {
    if (!this.published) return false;

    final var section = this.searchSectionOrElseThrow(item -> item.getId().equals(eventSectionId));

    return section.allowReserveSpot(eventSpotId);
  }

  public void changeLocation(final UpdateSpotLocationCommand command) {
    final var section = this.sections.stream()
      .filter(item -> item.getId().equals(command.sectionId()))
      .findFirst()
      .orElseThrow(() -> new DomainEntityNotFoundException("Section not found"));

    section.changeLocation(command);
  }

  public void markSpotAsReserved(final EventSectionId eventSectionId, final EventSpotId eventSpotId) {
    final var section = this.searchSectionOrElseThrow(item -> item.getId().equals(eventSectionId));
    section.markSpotAsReserved(eventSpotId);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, Entity.class);
  }

}
