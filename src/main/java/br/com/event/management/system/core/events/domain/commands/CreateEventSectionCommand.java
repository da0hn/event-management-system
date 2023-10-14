package br.com.event.management.system.core.events.domain.commands;

import java.math.BigDecimal;

public record CreateEventSectionCommand(
  String name,
  String description,
  Long totalSpots,
  BigDecimal price
) {
}
