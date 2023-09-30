package br.com.event.management.system.events.infrastructure.database.repositories.impl;

import br.com.event.management.system.common.domain.EntityId;
import br.com.event.management.system.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.events.domain.entities.Order;
import br.com.event.management.system.events.domain.repositories.OrderRepository;
import br.com.event.management.system.events.infrastructure.database.mappers.OrderModelMapper;
import br.com.event.management.system.events.infrastructure.database.repositories.OrderModelJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class OrderModelMysqlRepository implements OrderRepository {

  private final OrderModelJpaRepository orderModelJpaRepository;

  private final OrderModelMapper orderModelMapper;

  @Override
  public void add(final Order entity) {
    this.orderModelJpaRepository.save(this.orderModelMapper.toModel(entity));
  }

  @Override
  public Optional<Order> findById(final EntityId<UUID> entityId) {
    return this.orderModelJpaRepository.findById(entityId.value())
      .map(this.orderModelMapper::toDomain);
  }

  @Override
  public Order findByIdOrElseThrow(final EntityId<UUID> id) throws DomainEntityNotFoundException {
    return this.findById(id)
      .orElseThrow(() -> new DomainEntityNotFoundException("Order not found"));
  }

  @Override
  public List<Order> findAll() {
    return this.orderModelJpaRepository.findAll().stream()
      .map(this.orderModelMapper::toDomain)
      .toList();
  }

  @Override
  public void remove(final Order id) {
    this.orderModelJpaRepository.delete(this.orderModelMapper.toModel(id));
  }

}
