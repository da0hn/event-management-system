package br.com.event.management.system.events.infrastructure.database.repositories.impl;

import br.com.event.management.system.common.domain.EntityId;
import br.com.event.management.system.common.domain.IRepository;
import br.com.event.management.system.events.domain.entities.Partner;
import br.com.event.management.system.events.infrastructure.database.mappers.PartnerModelMapper;
import br.com.event.management.system.events.infrastructure.database.repositories.PartnerModelJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PartnerMysqlRepository implements IRepository<Partner> {

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
