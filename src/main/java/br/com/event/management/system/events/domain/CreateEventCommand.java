package br.com.event.management.system.events.domain;

import br.com.event.management.system.common.domain.valueobjects.PartnerId;

import java.time.LocalDate;

public record CreateEventCommand(
  String name,
  String description,
  LocalDate date,
  PartnerId partnerId
) {
}
