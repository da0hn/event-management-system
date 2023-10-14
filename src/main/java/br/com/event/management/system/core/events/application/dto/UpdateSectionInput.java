package br.com.event.management.system.core.events.application.dto;

import java.util.UUID;

public record UpdateSectionInput(
  UUID eventId,
  UUID sectionId,
  String name,
  String description
) {
}
