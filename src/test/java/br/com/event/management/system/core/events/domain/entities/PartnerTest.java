package br.com.event.management.system.core.events.domain.entities;

import br.com.event.management.system.core.events.domain.commands.CreatePartnerCommand;
import br.com.event.management.system.core.events.domain.commands.InitializeEventCommand;
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
  @DisplayName("Should create an partner")
  void test1() {
    final var partner = Partner.create(new CreatePartnerCommand("Partner 1"));

    Assertions.assertThat(partner.getId()).isNotNull();
    Assertions.assertThat(partner.getName()).isEqualTo("Partner 1");
    Assertions.assertThat(partner.getEvents()).hasSize(1);
  }

  @Test
  @DisplayName("Should change partner name")
  void test2() {
    final var partner = Partner.create(new CreatePartnerCommand("Partner 1"));

    Assertions.assertThat(partner.getId()).isNotNull();
    Assertions.assertThat(partner.getName()).isEqualTo("Partner 1");
    Assertions.assertThat(partner.getEvents()).hasSize(1);

    partner.changeName("Partner Name Changed");

    Assertions.assertThat(partner.getName()).isEqualTo("Partner Name Changed");
    Assertions.assertThat(partner.getEvents()).hasSize(2);
  }

  @Test
  @DisplayName("Should initialize a event")
  void test3() {
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
