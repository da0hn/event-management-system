package br.com.event.management.system.events.domain;

import java.math.BigDecimal;

public record CreateEventSectionCommand(
  String name,
  String description,
  Long totalSpots,
  BigDecimal price
) {
}
