package br.com.event.management.system.core.common.domain;

@FunctionalInterface
public interface DomainEventPublisher {

  void publish(final AggregateRoot<?> aggregateRoot);

}
