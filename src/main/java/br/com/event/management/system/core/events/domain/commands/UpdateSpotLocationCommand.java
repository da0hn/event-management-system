package br.com.event.management.system.core.events.domain.commands;

import br.com.event.management.system.core.common.domain.valueobjects.EventSectionId;
import br.com.event.management.system.core.common.domain.valueobjects.EventSpotId;

public record UpdateSpotLocationCommand(
  EventSectionId sectionId,
  EventSpotId spotId,
  String location
) {
}
