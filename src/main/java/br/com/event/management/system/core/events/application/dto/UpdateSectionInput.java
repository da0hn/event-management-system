package br.com.event.management.system.core.events.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record UpdateSectionInput(
  @JsonIgnore
  UUID eventId,
  UUID sectionId,
  String name,
  String description
) {
}
