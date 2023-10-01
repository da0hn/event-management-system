package br.com.event.management.system.events.application.dto;

import java.util.UUID;

public record CreateOrderInput(
  UUID eventId,
  UUID sectionId,
  UUID spotId,
  UUID customerId
) {
}
