package br.com.event.management.system.events.infrastructure.database;

import br.com.event.management.system.events.domain.commands.CreateEventSectionCommand;
import br.com.event.management.system.events.domain.commands.CreatePartnerCommand;
import br.com.event.management.system.events.domain.entities.EventSection;
import br.com.event.management.system.events.domain.commands.InitializeEventCommand;
import br.com.event.management.system.events.domain.entities.Partner;
import br.com.event.management.system.events.infrastructure.database.repositories.impl.EventMysqlRepository;
import br.com.event.management.system.events.infrastructure.database.repositories.impl.PartnerMysqlRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;

@Testcontainers
@ActiveProfiles("test-containers")
@DisplayName("Event Repository Tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EventMysqlRepositoryTest {

  @Autowired
  private EventMysqlRepository repository;

  @Autowired
  private PartnerMysqlRepository partnerMysqlRepository;

  @Test
  @DisplayName("Should execute all repository operations")
  void test1() {

    final var partner = Partner.create(new CreatePartnerCommand("Partner 1"));
    this.partnerMysqlRepository.add(partner);

    final var date = LocalDate.now();
    final var event = partner.initializeEvent(new InitializeEventCommand(
      "Event 1",
      null,
      date
    ));

    event.addSection(new CreateEventSectionCommand("Section 1", null, 100L, BigDecimal.valueOf(120.00)));

    this.repository.add(event);

    final var maybeEvent = this.repository.findById(event.getId());

    Assertions.assertThat(maybeEvent).isPresent();
    Assertions.assertThat(maybeEvent.get().getId()).isEqualTo(event.getId());
    Assertions.assertThat(maybeEvent.get().getName()).isEqualTo(event.getName());
    Assertions.assertThat(maybeEvent.get().getPartnerId()).isEqualTo(event.getPartnerId());
    Assertions.assertThat(maybeEvent.get().getSections()).hasSize(1);
    Assertions.assertThat(maybeEvent.get().getDate()).isEqualTo(date);
    Assertions.assertThat(maybeEvent.get().getTotalSpots()).isEqualTo(100);
    Assertions.assertThat(maybeEvent.get().getSections()).flatMap(EventSection::getSpots).hasSize(100);

    event.changeName("Event 2");
    this.repository.add(event);

    final var maybeUpdatedEvent = this.repository.findById(event.getId());

    Assertions.assertThat(maybeUpdatedEvent).isPresent();
    Assertions.assertThat(maybeUpdatedEvent.get().getName()).isEqualTo("Event 2");
    Assertions.assertThat(maybeUpdatedEvent.get().getId()).isEqualTo(event.getId());
    Assertions.assertThat(maybeUpdatedEvent.get().getPartnerId()).isEqualTo(event.getPartnerId());
    Assertions.assertThat(maybeUpdatedEvent.get().getSections()).hasSize(1);
    Assertions.assertThat(maybeUpdatedEvent.get().getDate()).isEqualTo(date);
    Assertions.assertThat(maybeUpdatedEvent.get().getTotalSpots()).isEqualTo(100);
    Assertions.assertThat(maybeUpdatedEvent.get().getSections()).flatMap(EventSection::getSpots).hasSize(100);

    final var allEvents = this.repository.findAll();
    Assertions.assertThat(allEvents).hasSize(1);

    this.repository.remove(event);

    Assertions.assertThat(this.repository.findAll()).isEmpty();
  }

}
