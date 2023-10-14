package br.com.event.management.system.core.events.domain.commands;

import br.com.event.management.system.core.common.domain.valueobjects.EventSectionId;

public record UpdateSectionInformationCommand(
  EventSectionId sectionId,
  String name,
  String description
) {
}
