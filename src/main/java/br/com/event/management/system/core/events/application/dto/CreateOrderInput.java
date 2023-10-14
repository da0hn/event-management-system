package br.com.event.management.system.core.events.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record CreateOrderInput(
  @JsonIgnore
  UUID eventId,
  UUID sectionId,
  UUID spotId,
  UUID customerId,
  String cardToken
) {
}
