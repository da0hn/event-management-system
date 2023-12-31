package br.com.event.management.system.core.events.application.service;

import br.com.event.management.system.core.events.application.dto.AddSectionInput;
import br.com.event.management.system.core.events.application.dto.CreateEventInput;
import br.com.event.management.system.core.events.application.dto.UpdateEventInput;
import br.com.event.management.system.core.events.application.dto.UpdateSectionInput;
import br.com.event.management.system.core.events.domain.entities.Event;
import br.com.event.management.system.core.events.domain.entities.EventSection;
import br.com.event.management.system.core.events.domain.entities.EventSpot;
import br.com.event.management.system.core.events.application.dto.UpdateSpotLocationInput;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface EventService {

  List<Event> findAll();

  Event create(CreateEventInput input);

  Event update(UUID eventId, UpdateEventInput input);

  Set<EventSection> findSections(UUID eventId);

  Event addSection(AddSectionInput input);

  Event updateSection(UpdateSectionInput input);

  Set<EventSpot> findSpots(UUID eventId, UUID sectionId);

  EventSpot updateSpotLocation(UpdateSpotLocationInput input);

  Event publishAll(UUID eventId);

}
