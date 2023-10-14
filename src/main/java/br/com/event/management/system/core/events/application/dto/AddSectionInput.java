package br.com.event.management.system.core.events.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder(toBuilder = true)
public record AddSectionInput(
  String name,
  String description,
  Long totalSpots,
  BigDecimal price,
  @JsonIgnore
  UUID eventId
) {
}
