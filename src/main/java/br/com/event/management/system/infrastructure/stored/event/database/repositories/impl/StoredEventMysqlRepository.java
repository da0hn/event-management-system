package br.com.event.management.system.infrastructure.stored.event.database.repositories.impl;

import br.com.event.management.system.core.common.domain.EntityId;
import br.com.event.management.system.core.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.core.common.domain.valueobjects.StoredEventId;
import br.com.event.management.system.core.stored.events.domain.entities.StoredEvent;
import br.com.event.management.system.core.stored.events.repositories.StoredEventRepository;
import br.com.event.management.system.infrastructure.stored.event.database.mappers.StoredEventModelMapper;
import br.com.event.management.system.infrastructure.stored.event.database.model.StoredEventModel;
import br.com.event.management.system.infrastructure.stored.event.database.repositories.StoredEventModelJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StoredEventMysqlRepository implements StoredEventRepository {

  private final StoredEventModelJpaRepository repository;

  private final StoredEventModelMapper mapper;

  @Override
  public List<StoredEvent> allBetween(final StoredEventId lowStoredEventId, final StoredEventId highStoredEventId) {
    // FIXME: poor performance
    final var storedEvents = this.repository.findAll();

    final var lowStoredEvent = this.findByIdOrElseThrow(lowStoredEventId);
    final var highStoredEvent = this.findByIdOrElseThrow(highStoredEventId);

    return storedEvents.stream()
      .sorted(Comparator.comparing(StoredEventModel::getOccurredOn))
      .filter(entity ->
                entity.getOccurredOn().isAfter(lowStoredEvent.getOccurredOn()) &&
                entity.getOccurredOn().isBefore(highStoredEvent.getOccurredOn())
      )
      .map(this.mapper::toDomain)
      .collect(Collectors.toList());
  }

  @Override
  public List<StoredEvent> allSince(final StoredEventId storedEventId) {
    final var storedEvent = this.findByIdOrElseThrow(storedEventId);

    return this.repository.findAll().stream()
      .filter(entity -> entity.getOccurredOn().isAfter(storedEvent.getOccurredOn()))
      .map(this.mapper::toDomain)
      .collect(Collectors.toList());
  }

  @Override
  public StoredEvent add(final StoredEvent storedEvent) {
    final var model = this.mapper.toModel(storedEvent);
    return this.mapper.toDomain(this.repository.save(model));
  }

  @Override
  public StoredEventModel findByIdOrElseThrow(final EntityId<UUID> id) throws DomainEntityNotFoundException {
    return this.findById(id)
      .orElseThrow(() -> new DomainEntityNotFoundException("Stored Event not found"));
  }

  private Optional<StoredEventModel> findById(final EntityId<UUID> id) {
    return this.repository.findById(id.value());
  }

}
