package br.com.event.management.system.events.application.dto;

import java.time.LocalDate;

public record UpdateEventInput(
  String name,
  String description,
  LocalDate date
) {
}
