package br.com.event.management.system.common.domain;

public abstract class AggregateRoot<ID extends EntityId<?>> extends Entity<ID> {

  protected AggregateRoot(final ID id) {
    super(id);
  }

}
