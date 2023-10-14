package br.com.event.management.system.infrastructure.events.database.repositories.impl;

import br.com.event.management.system.core.common.domain.EntityId;
import br.com.event.management.system.core.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.core.events.domain.entities.Customer;
import br.com.event.management.system.infrastructure.events.database.mappers.CustomerModelMapper;
import br.com.event.management.system.core.events.domain.repositories.CustomerRepository;
import br.com.event.management.system.infrastructure.events.database.repositories.CustomerModelJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CustomerMysqlRepository implements CustomerRepository {

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
  public Customer findByIdOrElseThrow(final EntityId<UUID> id) throws DomainEntityNotFoundException {
    return this.findById(id)
      .orElseThrow(() -> new DomainEntityNotFoundException("Customer not found"));
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
