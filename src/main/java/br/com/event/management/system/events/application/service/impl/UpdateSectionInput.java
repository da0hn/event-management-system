package br.com.event.management.system.events.application.service.impl;

import java.util.UUID;

public record UpdateSectionInput(
  UUID eventId,
  UUID sectionId,
  String name,
  String description
) {
}
