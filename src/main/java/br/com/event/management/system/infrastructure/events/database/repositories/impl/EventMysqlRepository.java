package br.com.event.management.system.infrastructure.events.database.repositories.impl;

import br.com.event.management.system.core.common.domain.EntityId;
import br.com.event.management.system.core.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.core.events.domain.entities.Event;
import br.com.event.management.system.infrastructure.events.database.mappers.EventModelMapper;
import br.com.event.management.system.infrastructure.events.database.repositories.EventModelJpaRepository;
import br.com.event.management.system.core.events.domain.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EventMysqlRepository implements EventRepository {

  private final EventModelJpaRepository eventModelJpaRepository;

  private final EventModelMapper eventModelMapper;

  @Override
  public void add(final Event entity) {
    final var eventModel = this.eventModelMapper.toModel(entity);
    this.eventModelJpaRepository.save(eventModel);
  }

  @Override
  public Optional<Event> findById(final EntityId<UUID> id) {
    return this.eventModelJpaRepository.findById(id.value())
      .map(this.eventModelMapper::toDomain);
  }

  @Override
  public Event findByIdOrElseThrow(final EntityId<UUID> id) throws DomainEntityNotFoundException {
    return this.findById(id)
      .orElseThrow(() -> new DomainEntityNotFoundException("Event not found"));
  }

  @Override
  public List<Event> findAll() {
    return this.eventModelJpaRepository.findAll().stream()
      .map(this.eventModelMapper::toDomain)
      .collect(Collectors.toList());
  }

  @Override
  public void remove(final Event id) {
    this.eventModelJpaRepository.delete(this.eventModelMapper.toModel(id));
  }

}
