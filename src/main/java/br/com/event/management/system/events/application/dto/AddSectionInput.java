package br.com.event.management.system.events.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AddSectionInput(
  String name,
  String description,
  Long totalSpots,
  BigDecimal price,
  UUID eventId
) {
}
