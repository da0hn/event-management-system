package br.com.event.management.system.core.events.domain.commands;

import java.time.LocalDate;

public record InitializeEventCommand(
  String name,
  String description,
  LocalDate date
) {
}
