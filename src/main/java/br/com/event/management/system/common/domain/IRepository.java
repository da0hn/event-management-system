package br.com.event.management.system.common.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRepository<E extends AggregateRoot<? extends EntityId<UUID>>> {

  void add(E entity);

  Optional<E> findById(EntityId<UUID> id);

  List<E> findAll();

  void remove(E id);

}
