package br.com.event.management.system.application.events.events;

import br.com.event.management.system.core.events.application.dto.CreateEventInput;
import br.com.event.management.system.core.events.application.service.EventService;
import br.com.event.management.system.core.events.domain.entities.Event;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController {

  private final EventService eventService;

  @GetMapping
  public ResponseEntity<List<Event>> list() {
    return ResponseEntity.ok(this.eventService.findAll());
  }

  @PostMapping
  public ResponseEntity<Event> create(@RequestBody final CreateEventInput input) {
    final var response = this.eventService.create(input);
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/{eventId}/publish-all")
  public ResponseEntity<Event> publishAll(@PathVariable final UUID eventId) {
    final var response = this.eventService.publishAll(eventId);
    return ResponseEntity.ok(response);
  }

}
