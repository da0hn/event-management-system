package br.com.event.management.system.events.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record CreateEventInput(
  UUID partnerId,
  String name,
  String description,
  LocalDate date
) {

}
