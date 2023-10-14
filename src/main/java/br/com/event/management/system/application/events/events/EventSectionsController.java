package br.com.event.management.system.application.events.events;

import br.com.event.management.system.core.events.application.dto.AddSectionInput;
import br.com.event.management.system.core.events.application.dto.UpdateSectionInput;
import br.com.event.management.system.core.events.application.service.EventService;
import br.com.event.management.system.core.events.domain.entities.Event;
import br.com.event.management.system.core.events.domain.entities.EventSection;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/events/{eventId}/sections")
public class EventSectionsController {

  private final EventService eventService;

  @GetMapping
  public ResponseEntity<Set<EventSection>> list(@PathVariable final UUID eventId) {
    return ResponseEntity.ok(this.eventService.findSections(eventId));
  }

  @PostMapping
  public ResponseEntity<Event> addSection(@PathVariable final UUID eventId, @RequestBody final AddSectionInput input) {
    final var response = this.eventService.addSection(
      input.toBuilder()
        .eventId(eventId)
        .build()
    );
    return ResponseEntity.ok(response);
  }

  @PutMapping
  public ResponseEntity<Event> updateSection(@PathVariable final UUID eventId, final UpdateSectionInput input) {
    final var response = this.eventService.updateSection(
      input.toBuilder()
        .eventId(eventId)
        .build()
    );
    return ResponseEntity.ok(response);
  }

}
