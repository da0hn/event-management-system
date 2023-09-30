package br.com.event.management.system.events.domain.commands;

import br.com.event.management.system.common.domain.valueobjects.EventSectionId;
import br.com.event.management.system.common.domain.valueobjects.EventSpotId;

public record UpdateSpotLocationCommand(
  EventSectionId sectionId,
  EventSpotId spotId,
  String location
) {
}
