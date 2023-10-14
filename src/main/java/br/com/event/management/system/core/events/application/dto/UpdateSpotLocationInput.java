package br.com.event.management.system.core.events.application.dto;

import java.util.UUID;

public record UpdateSpotLocationInput(
  UUID eventId,
  UUID sectionId,
  UUID spotId,
  String location
) {
}
