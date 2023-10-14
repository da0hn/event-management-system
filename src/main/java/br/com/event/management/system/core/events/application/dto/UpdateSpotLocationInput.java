package br.com.event.management.system.core.events.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record UpdateSpotLocationInput(
  @JsonIgnore
  UUID eventId,
  @JsonIgnore
  UUID sectionId,
  @JsonIgnore
  UUID spotId,
  String location
) {
}
