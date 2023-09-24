package br.com.event.management.system.events.domain.entities;

import br.com.event.management.system.events.domain.commands.CreatePartnerCommand;
import br.com.event.management.system.events.domain.commands.InitializeEventCommand;
import br.com.event.management.system.events.domain.entities.Partner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
class PartnerTest {

  @Test
  @DisplayName("Should initialize a event")
  void test1() {
    final var partner = Partner.create(new CreatePartnerCommand("Partner 1"));

    final var event = partner.initializeEvent(new InitializeEventCommand(
      "Event 1",
      null,
      LocalDate.now()
    ));

    Assertions.assertThat(event.getPartnerId()).isEqualTo(partner.getId());
    Assertions.assertThat(event.getTotalSpots()).isZero();
    Assertions.assertThat(event.getTotalSpotsReserved()).isZero();
    Assertions.assertThat(event.isPublished()).isFalse();
    Assertions.assertThat(event.getSections()).isEmpty();
  }

}
