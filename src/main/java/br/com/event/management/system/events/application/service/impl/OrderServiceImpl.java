package br.com.event.management.system.events.application.service.impl;

import br.com.event.management.system.common.application.UnitOfWork;
import br.com.event.management.system.common.domain.exception.DomainBusinessException;
import br.com.event.management.system.common.domain.valueobjects.CustomerId;
import br.com.event.management.system.common.domain.valueobjects.EventId;
import br.com.event.management.system.common.domain.valueobjects.EventSectionId;
import br.com.event.management.system.common.domain.valueobjects.EventSpotId;
import br.com.event.management.system.events.application.dto.CreateOrderInput;
import br.com.event.management.system.events.application.service.OrderService;
import br.com.event.management.system.events.domain.commands.CreateSpotReservationCommand;
import br.com.event.management.system.events.domain.entities.Order;
import br.com.event.management.system.events.domain.entities.SpotReservation;
import br.com.event.management.system.events.domain.repositories.CustomerRepository;
import br.com.event.management.system.events.domain.repositories.EventRepository;
import br.com.event.management.system.events.domain.repositories.OrderRepository;
import br.com.event.management.system.events.domain.repositories.SpotReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final CustomerRepository customerRepository;

  private final EventRepository eventRepository;

  private final SpotReservationRepository spotReservationRepository;

  private final UnitOfWork unitOfWork;

  @Override
  public List<Order> findAll() {
    return this.orderRepository.findAll();
  }

  @Override
  public Order create(final CreateOrderInput input) {
    final var customer = this.customerRepository.findByIdOrElseThrow(CustomerId.of(input.customerId()));

    final var event = this.eventRepository.findByIdOrElseThrow(EventId.of(input.eventId()));

    final var eventSectionId = EventSectionId.of(input.sectionId());
    final var eventSpotId = EventSpotId.of(input.spotId());

    if (event.allowReserveSpot(eventSectionId, eventSpotId)) {
      throw new DomainBusinessException("Spot not available");
    }

    if (this.spotReservationRepository.findById(eventSpotId).isPresent()) {
      throw new DomainBusinessException("Spot already reserved");
    }

    final var section = event.searchSectionOrElseThrow(item -> item.getId().equals(eventSectionId));

    return this.unitOfWork.execute(
      transaction -> {
        final var spotReservation = SpotReservation.create(new CreateSpotReservationCommand(eventSpotId, customer.getId()));

        this.spotReservationRepository.add(spotReservation);

        transaction.commit();

        final var order = Order.create(customer.getId(), section.getPrice(), eventSpotId);

        this.orderRepository.add(order);

        transaction.commit();

        event.markSpotAsReserved(eventSectionId, eventSpotId);

        this.eventRepository.add(event);

        return order;
      },
      (transaction, exception) -> {
        final var order = Order.create(customer.getId(), section.getPrice(), eventSpotId);
        order.cancel();
        this.orderRepository.add(order);
        throw new DomainBusinessException("An error occurred while creating the order");
      }
    );
  }

}
