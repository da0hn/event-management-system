package br.com.event.management.system.application.events.orders;

import br.com.event.management.system.core.events.application.dto.CreateOrderInput;
import br.com.event.management.system.core.events.application.service.OrderService;
import br.com.event.management.system.core.events.domain.entities.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/events/{eventId}/orders")
public class OrdersController {

  private final OrderService orderService;

  @GetMapping
  public ResponseEntity<List<Order>> list(@PathVariable final UUID eventId) {
    final var response = this.orderService.findAll();
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Order> create(@PathVariable final UUID eventId, @RequestBody final CreateOrderInput input) {
    final var response = this.orderService.create(
      input.toBuilder()
        .eventId(eventId)
        .build()
    );
    return ResponseEntity.ok(response);
  }

}
