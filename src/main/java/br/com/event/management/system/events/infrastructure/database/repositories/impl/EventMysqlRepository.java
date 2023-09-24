package br.com.event.management.system.events.infrastructure.database.repositories.impl;

import br.com.event.management.system.common.domain.EntityId;
import br.com.event.management.system.common.domain.IRepository;
import br.com.event.management.system.events.domain.Event;
import br.com.event.management.system.events.infrastructure.database.mappers.EventModelMapper;
import br.com.event.management.system.events.infrastructure.database.repositories.EventModelJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EventMysqlRepository implements IRepository<Event> {

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
