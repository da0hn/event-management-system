package br.com.event.management.system.core.common.domain;

public abstract class AggregateRoot<ID extends EntityId<?>> extends Entity<ID> {

  protected AggregateRoot(final ID id) {
    super(id);
  }

}
