package br.com.event.management.system.common.domain;

import br.com.event.management.system.common.domain.exception.DomainEntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRepository<E extends AggregateRoot<? extends EntityId<UUID>>> {

  void add(E entity);

  Optional<E> findById(EntityId<UUID> id);

  E findByIdOrElseThrow(EntityId<UUID> id) throws DomainEntityNotFoundException;

  List<E> findAll();

  void remove(E id);

}
