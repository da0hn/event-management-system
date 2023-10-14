package br.com.event.management.system.application.events.events;

import br.com.event.management.system.core.events.application.dto.UpdateSpotLocationInput;
import br.com.event.management.system.core.events.application.service.EventService;
import br.com.event.management.system.core.events.domain.entities.EventSpot;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/events/{eventId}/sections/{sectionId}/spots")
public class EventSpotsController {

  private final EventService eventService;

  @GetMapping
  public ResponseEntity<Set<EventSpot>> list(
    @PathVariable final UUID eventId,
    @PathVariable final UUID sectionId
  ) {
    return ResponseEntity.ok(this.eventService.findSpots(eventId, sectionId));
  }

  @PatchMapping("/{spotId}")
  public ResponseEntity<EventSpot> updateLocation(
    @PathVariable final UUID eventId,
    @PathVariable final UUID sectionId,
    @PathVariable final UUID spotId,
    @RequestBody final UpdateSpotLocationInput input
  ) {
    final var response = this.eventService.updateSpotLocation(
      input.toBuilder()
        .eventId(eventId)
        .sectionId(sectionId)
        .spotId(spotId)
        .build()
    );
    return ResponseEntity.ok(response);
  }

}
