package br.com.event.management.system.core.events.application.service;

import br.com.event.management.system.core.events.application.dto.CreateOrderInput;
import br.com.event.management.system.core.events.domain.entities.Order;

import java.util.List;

public interface OrderService {

  List<Order> findAll();

  Order create(CreateOrderInput input);

}
