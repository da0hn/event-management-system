package br.com.event.management.system.infrastructure.messaging;

import br.com.event.management.system.core.common.domain.AggregateRoot;
import br.com.event.management.system.core.common.domain.DomainEventPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class DomainEventPublisherSpringAdapter implements DomainEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void publish(final AggregateRoot<?> aggregateRoot) {

    if (Objects.isNull(aggregateRoot)) {
      throw new IllegalArgumentException("Aggregate root must not be null");
    }

    log.debug(
      "Publishing {} domain events from aggregate root: {}",
      aggregateRoot.getEvents().size(),
      aggregateRoot.getClass().getSimpleName()
    );

    for (final var domainEvent : aggregateRoot.getEvents()) {
      this.applicationEventPublisher.publishEvent(domainEvent);
    }
    aggregateRoot.clearEvents();
  }

}
