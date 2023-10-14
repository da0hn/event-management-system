package br.com.event.management.system.core.common.domain;

import lombok.Getter;

@Getter
public abstract class Entity<ID extends EntityId<?>> {

  private final ID id;

  protected Entity(final ID id) { this.id = id; }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final Entity<?> entity = (Entity<?>) o;

    return this.id.equals(entity.id);
  }

  @Override
  public abstract String toString();

}
