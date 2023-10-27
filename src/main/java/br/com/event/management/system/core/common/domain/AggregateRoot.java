package br.com.event.management.system.core.common.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class AggregateRoot<ID extends EntityId<?>> extends Entity<ID> {

  private final Set<DomainEvent> events = new HashSet<>();

  protected AggregateRoot(final ID id) {
    super(id);
  }

  protected void addEvent(final DomainEvent event) {
    this.events.add(event);
  }

  public void clearEvents() {
    this.events.clear();
  }

  public Set<DomainEvent> getEvents() {
    return Collections.unmodifiableSet(this.events);
  }

}
