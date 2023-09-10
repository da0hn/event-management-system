package br.com.event.management.system.common.domain;

import lombok.Getter;

@Getter
public abstract class AggregateRoot<ID extends EntityId<?>> extends Entity {

  private final ID id;

  protected AggregateRoot(final ID id) { this.id = id; }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final AggregateRoot<?> that = (AggregateRoot<?>) o;

    return this.id.equals(that.id);
  }

}
