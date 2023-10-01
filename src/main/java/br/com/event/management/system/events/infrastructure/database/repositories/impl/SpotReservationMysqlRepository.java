package br.com.event.management.system.events.infrastructure.database.repositories.impl;

import br.com.event.management.system.common.domain.EntityId;
import br.com.event.management.system.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.events.domain.entities.SpotReservation;
import br.com.event.management.system.events.domain.repositories.SpotReservationRepository;
import br.com.event.management.system.events.infrastructure.database.mappers.EventSpotReservationModelMapper;
import br.com.event.management.system.events.infrastructure.database.repositories.EventSpotReservationModelJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SpotReservationMysqlRepository implements SpotReservationRepository {

  private final EventSpotReservationModelJpaRepository repository;

  private final EventSpotReservationModelMapper eventSpotReservationModelMapper;

  @Override
  public void add(final SpotReservation entity) {
    final var eventModel = this.eventSpotReservationModelMapper.toModel(entity);
    this.repository.save(eventModel);
  }

  @Override
  public Optional<SpotReservation> findById(final EntityId<UUID> id) {
    return this.repository.findById(id.value())
      .map(this.eventSpotReservationModelMapper::toDomain);
  }

  @Override
  public SpotReservation findByIdOrElseThrow(final EntityId<UUID> id) throws DomainEntityNotFoundException {
    return this.findById(id)
      .orElseThrow(() -> new DomainEntityNotFoundException("Spot Reservation not found"));
  }

  @Override
  public List<SpotReservation> findAll() {
    return this.repository.findAll().stream()
      .map(this.eventSpotReservationModelMapper::toDomain)
      .collect(Collectors.toList());
  }

  @Override
  public void remove(final SpotReservation id) {
    this.repository.delete(this.eventSpotReservationModelMapper.toModel(id));
  }

}
