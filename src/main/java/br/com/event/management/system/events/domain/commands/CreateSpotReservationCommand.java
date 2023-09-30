package br.com.event.management.system.events.domain.commands;

import br.com.event.management.system.common.domain.valueobjects.CustomerId;
import br.com.event.management.system.common.domain.valueobjects.EventSpotId;

public record CreateSpotReservationCommand(
  EventSpotId spotId,
  CustomerId customerId
) {
}
