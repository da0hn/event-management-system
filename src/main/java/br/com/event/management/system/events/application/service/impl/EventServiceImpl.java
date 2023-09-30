package br.com.event.management.system.events.application.service.impl;

import br.com.event.management.system.common.application.UnitOfWork;
import br.com.event.management.system.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.common.domain.valueobjects.EventId;
import br.com.event.management.system.common.domain.valueobjects.EventSectionId;
import br.com.event.management.system.common.domain.valueobjects.EventSpotId;
import br.com.event.management.system.common.domain.valueobjects.PartnerId;
import br.com.event.management.system.events.application.dto.AddSectionInput;
import br.com.event.management.system.events.application.dto.CreateEventInput;
import br.com.event.management.system.events.application.dto.UpdateEventInput;
import br.com.event.management.system.events.application.dto.UpdateSectionInput;
import br.com.event.management.system.events.application.dto.UpdateSpotLocationInput;
import br.com.event.management.system.events.application.service.EventService;
import br.com.event.management.system.events.domain.commands.CreateEventSectionCommand;
import br.com.event.management.system.events.domain.commands.InitializeEventCommand;
import br.com.event.management.system.events.domain.commands.UpdateSectionInformationCommand;
import br.com.event.management.system.events.domain.commands.UpdateSpotLocationCommand;
import br.com.event.management.system.events.domain.entities.Event;
import br.com.event.management.system.events.domain.entities.EventSection;
import br.com.event.management.system.events.domain.entities.EventSpot;
import br.com.event.management.system.events.domain.repositories.EventRepository;
import br.com.event.management.system.events.domain.repositories.PartnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;

  private final PartnerRepository partnerRepository;

  private final UnitOfWork unitOfWork;

  @Override
  public List<Event> findAll() {
    return this.eventRepository.findAll();
  }

  @Override
  public Event create(final CreateEventInput input) {
    return this.unitOfWork.execute((transaction) -> {
      final var partner = this.partnerRepository.findById(PartnerId.of(input.partnerId()))
        .orElseThrow(() -> new DomainEntityNotFoundException("Partner not found"));

      final var newEvent = partner.initializeEvent(new InitializeEventCommand(
        input.name(),
        input.description(),
        input.date()
      ));

      this.eventRepository.add(newEvent);

      return newEvent;
    });
  }

  @Override
  public Event update(final UUID eventId, final UpdateEventInput input) {
    return this.unitOfWork.execute((transaction) -> {
      final var event = this.findEventOrElseThrow(eventId);

      event.changeName(input.name());
      event.changeDescription(input.description());
      event.changeDate(input.date());

      this.eventRepository.add(event);
      return event;
    });
  }

  @Override
  public Set<EventSection> findSections(final UUID eventId) {
    final var event = this.findEventOrElseThrow(eventId);
    return event.getSections();
  }

  @Override
  public Event addSection(final AddSectionInput input) {
    return this.unitOfWork.execute((transaction -> {
      final var event = this.findEventOrElseThrow(input.eventId());

      event.addSection(new CreateEventSectionCommand(
        input.name(),
        input.description(),
        input.totalSpots(),
        input.price()
      ));

      this.eventRepository.add(event);

      return event;
    }));
  }

  @Override
  public Event updateSection(final UpdateSectionInput input) {
    return this.unitOfWork.execute((transaction -> {
      final var event = this.findEventOrElseThrow(input.eventId());

      event.changeSectionInformation(new UpdateSectionInformationCommand(
        EventSectionId.of(input.sectionId()),
        input.name(),
        input.description()
      ));

      this.eventRepository.add(event);

      return event;
    }));
  }

  @Override
  public Set<EventSpot> findSpots(final UUID eventId, final UUID sectionId) {
    final var event = this.findEventOrElseThrow(eventId);

    return event.getSections().stream()
      .filter(item -> item.getId().equals(EventSectionId.of(sectionId)))
      .findFirst()
      .map(EventSection::getSpots)
      .orElseThrow(() -> new DomainEntityNotFoundException("Section not found"));
  }

  @Override
  public EventSpot updateSpotLocation(final UpdateSpotLocationInput input) {
    return this.unitOfWork.execute((transaction -> {
      final var event = this.findEventOrElseThrow(input.eventId());

      event.changeLocation(new UpdateSpotLocationCommand(
        EventSectionId.of(input.sectionId()),
        EventSpotId.of(input.spotId()),
        input.location()
      ));

      this.eventRepository.add(event);

      return event.getSections().stream()
        .filter(item -> item.getId().equals(EventSectionId.of(input.sectionId())))
        .map(EventSection::getSpots)
        .flatMap(Set::stream)
        .filter(item -> item.getId().equals(EventSpotId.of(input.spotId())))
        .findFirst()
        .orElseThrow(() -> new DomainEntityNotFoundException("Spot not found"));
    }));
  }

  @Override
  public Event publishAll(final UUID eventId) {
    return this.unitOfWork.execute((transaction -> {
      final var event = this.findEventOrElseThrow(eventId);
      event.publishAll();
      this.eventRepository.add(event);
      return event;
    }));
  }

  private Event findEventOrElseThrow(final UUID id) {
    return this.eventRepository.findById(EventId.of(id))
      .orElseThrow(() -> new DomainEntityNotFoundException("Event not found"));
  }

}
