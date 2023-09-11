package br.com.event.management.system.events.domain;

import br.com.event.management.system.common.domain.valueobjects.PartnerId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
class EventTest {

  private static final long TOTAL_SPOTS = 100L;

  @Test
  @DisplayName("Should create event")
  void test1() {
    final var event = Event.create(new CreateEventCommand(
      "Event 1",
      null,
      LocalDate.now(),
      PartnerId.newInstance()
    ));

    event.addSection(new CreateEventSectionCommand(
      "Event 1 Section 1",
      null,
      TOTAL_SPOTS,
      BigDecimal.valueOf(500)
    ));

    Assertions.assertThat(event.getSections()).isUnmodifiable();
    Assertions.assertThat(event.getSections()).hasSize(1);
    Assertions.assertThat(event.getTotalSpots()).isEqualTo(TOTAL_SPOTS);
  }

  @Test
  @DisplayName("Should create event section")
  void test2() {
    final var section = EventSection.create(new CreateEventSectionCommand(
      "Event 1 Section 1",
      null,
      100L,
      BigDecimal.valueOf(100)
    ));

    Assertions.assertThat(section.getTotalSpots()).isEqualTo(100);
    Assertions.assertThat(section.getSpots()).hasSize(100);
    Assertions.assertThat(section.getSpots()).isUnmodifiable();
  }

  @Test
  @DisplayName("Should publish all spots of event")
  void test3() {
    final var event = Event.create(new CreateEventCommand(
      "Event 1",
      null,
      LocalDate.now(),
      PartnerId.newInstance()
    ));

    event.addSection(new CreateEventSectionCommand(
      "Section 1",
      null,
      100L,
      BigDecimal.valueOf(1000)
    ));

    event.addSection(new CreateEventSectionCommand(
      "Section 2",
      null,
      1000L,
      BigDecimal.valueOf(50)
    ));

    event.publishAll();

    Assertions.assertThat(event.isPublished()).isTrue();
    Assertions.assertThat(event.getSections()).allMatch(EventSection::isPublished);
    Assertions.assertThat(event.getSections().stream())
      .flatMap(EventSection::getSpots)
      .allMatch(EventSpot::isPublished);

  }

}
