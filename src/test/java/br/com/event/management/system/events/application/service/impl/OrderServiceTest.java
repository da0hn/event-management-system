package br.com.event.management.system.events.application.service.impl;

import br.com.event.management.system.events.application.dto.CreateOrderInput;
import br.com.event.management.system.events.application.service.OrderService;
import br.com.event.management.system.events.domain.commands.CreateCustomerCommand;
import br.com.event.management.system.events.domain.commands.CreateEventSectionCommand;
import br.com.event.management.system.events.domain.commands.CreatePartnerCommand;
import br.com.event.management.system.events.domain.commands.InitializeEventCommand;
import br.com.event.management.system.events.domain.entities.Customer;
import br.com.event.management.system.events.domain.entities.Partner;
import br.com.event.management.system.events.domain.repositories.CustomerRepository;
import br.com.event.management.system.events.domain.repositories.EventRepository;
import br.com.event.management.system.events.domain.repositories.PartnerRepository;
import br.com.event.management.system.events.domain.repositories.SpotReservationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Testcontainers
@ActiveProfiles("test-containers")
@DisplayName("Order Application Service Tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrderServiceTest {

  @Autowired
  private OrderService orderService;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private PartnerRepository partnerRepository;

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private SpotReservationRepository spotReservationRepository;

  @Test
  @DisplayName("Should create order")
  void test1() {

    final var customer = Customer.create(new CreateCustomerCommand("Customer 1", "13088528009"));
    this.customerRepository.add(customer);

    final var partner = Partner.create(new CreatePartnerCommand("Partner 1"));
    this.partnerRepository.add(partner);

    final var event = partner.initializeEvent(new InitializeEventCommand("Event 1", "Description 1", LocalDate.now()));

    event.addSection(new CreateEventSectionCommand("Section 1", null, 1000L, new BigDecimal(150)));
    event.publishAll();

    this.eventRepository.add(event);

    final var section = new ArrayList<>(event.getSections()).get(0);
    final var eventSpot = new ArrayList<>(section.getSpots()).get(0);

    final var asyncOrder1 = CompletableFuture.supplyAsync(
      () -> this.orderService.create(new CreateOrderInput(
        event.getId().value(),
        section.getId().value(),
        eventSpot.getId().value(),
        customer.getId().value(),
        "card-token"
      ))
    );
    final var asyncOrder2 = CompletableFuture.supplyAsync(
      () -> this.orderService.create(new CreateOrderInput(
        event.getId().value(),
        section.getId().value(),
        eventSpot.getId().value(),
        customer.getId().value(),
        "card-token"
      ))
    );
    final var asyncAll = CompletableFuture.allOf(asyncOrder1, asyncOrder2);

    while (Future.State.RUNNING == asyncAll.state()) ;

    final var orders = this.orderService.findAll();
    final var reservedSpots = this.spotReservationRepository.findAll();

    Assertions.assertThat(orders).hasSize(2);
    Assertions.assertThat(reservedSpots).hasSize(1);
  }

}
