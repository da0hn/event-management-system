package br.com.event.management.system.events.application.dto;

import java.util.UUID;

public record UpdateSectionInput(
  UUID eventId,
  UUID sectionId,
  String name,
  String description
) {
}
