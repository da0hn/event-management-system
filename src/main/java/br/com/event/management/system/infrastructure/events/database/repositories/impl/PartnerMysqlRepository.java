package br.com.event.management.system.infrastructure.events.database.repositories.impl;

import br.com.event.management.system.core.common.domain.EntityId;
import br.com.event.management.system.core.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.core.events.domain.entities.Partner;
import br.com.event.management.system.core.events.domain.repositories.PartnerRepository;
import br.com.event.management.system.infrastructure.events.database.mappers.PartnerModelMapper;
import br.com.event.management.system.infrastructure.events.database.repositories.PartnerModelJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PartnerMysqlRepository implements PartnerRepository {

  private final PartnerModelJpaRepository partnerModelJpaRepository;

  private final PartnerModelMapper partnerModelMapper;

  @Override
  public void add(final Partner entity) {
    this.partnerModelJpaRepository.save(this.partnerModelMapper.toModel(entity));
  }

  @Override
  public Optional<Partner> findById(final EntityId<UUID> entityId) {
    return this.partnerModelJpaRepository.findById(entityId.value())
      .map(this.partnerModelMapper::toDomain);
  }

  @Override
  public Partner findByIdOrElseThrow(final EntityId<UUID> id) throws DomainEntityNotFoundException {
    return this.findById(id)
      .orElseThrow(() -> new DomainEntityNotFoundException("Partner not found"));
  }

  @Override
  public List<Partner> findAll() {
    return this.partnerModelJpaRepository.findAll().stream()
      .map(this.partnerModelMapper::toDomain)
      .toList();
  }

  @Override
  public void remove(final Partner id) {
    this.partnerModelJpaRepository.delete(this.partnerModelMapper.toModel(id));
  }

}
