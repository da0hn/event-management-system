package br.com.event.management.system.core.events.domain.commands;

import br.com.event.management.system.core.common.domain.valueobjects.CustomerId;
import br.com.event.management.system.core.common.domain.valueobjects.EventSpotId;

public record CreateSpotReservationCommand(
  EventSpotId spotId,
  CustomerId customerId
) {
}
