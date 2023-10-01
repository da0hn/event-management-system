package br.com.event.management.system.events.application.service;

import br.com.event.management.system.events.application.dto.CreateOrderInput;
import br.com.event.management.system.events.domain.entities.Order;

import java.util.List;

public interface OrderService {

  List<Order> findAll();

  Order create(CreateOrderInput input);

}
