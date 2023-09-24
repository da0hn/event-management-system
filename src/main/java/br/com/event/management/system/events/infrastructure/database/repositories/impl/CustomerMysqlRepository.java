package br.com.event.management.system.events.infrastructure.database.repositories.impl;

import br.com.event.management.system.common.domain.EntityId;
import br.com.event.management.system.common.domain.IRepository;
import br.com.event.management.system.events.domain.entities.Customer;
import br.com.event.management.system.events.infrastructure.database.mappers.CustomerModelMapper;
import br.com.event.management.system.events.infrastructure.database.repositories.CustomerModelJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CustomerMysqlRepository implements IRepository<Customer> {

  private final CustomerModelJpaRepository customerModelJpaRepository;

  private final CustomerModelMapper customerModelMapper;

  @Override
  public void add(final Customer entity) {
    this.customerModelJpaRepository.save(this.customerModelMapper.toModel(entity));
  }

  @Override
  public Optional<Customer> findById(final EntityId<UUID> id) {
    return this.customerModelJpaRepository.findById(id.value())
      .map(this.customerModelMapper::toDomain);
  }

  @Override
  public List<Customer> findAll() {
    return this.customerModelJpaRepository.findAll().stream()
      .map(this.customerModelMapper::toDomain)
      .toList();
  }

  @Override
  public void remove(final Customer entity) {
    this.customerModelJpaRepository.delete(this.customerModelMapper.toModel(entity));
  }

}
