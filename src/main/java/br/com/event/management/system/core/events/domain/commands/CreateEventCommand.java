package br.com.event.management.system.core.events.domain.commands;

import br.com.event.management.system.core.common.domain.valueobjects.PartnerId;

import java.time.LocalDate;

public record CreateEventCommand(
  String name,
  String description,
  LocalDate date,
  PartnerId partnerId
) {
}
