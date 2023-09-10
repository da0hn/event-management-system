package br.com.event.management.system.common.domain;

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
  public abstract String toString();

}
