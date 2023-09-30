package br.com.event.management.system.events.domain.commands;

import br.com.event.management.system.common.domain.valueobjects.EventSectionId;

public record UpdateSectionInformationCommand(
  EventSectionId sectionId,
  String name,
  String description
) {
}
