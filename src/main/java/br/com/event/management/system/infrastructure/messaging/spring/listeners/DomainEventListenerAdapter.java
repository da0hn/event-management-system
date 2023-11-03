package br.com.event.management.system.infrastructure.messaging.spring.listeners;

import br.com.event.management.system.application.configuration.properties.ApplicationOutboxProperties;
import br.com.event.management.system.core.common.domain.DomainEvent;
import br.com.event.management.system.core.events.domain.events.PartnerChangedName;
import br.com.event.management.system.core.events.domain.events.PartnerCreated;
import br.com.event.management.system.core.events.domain.integration.event.PartnerCreatedIntegrationEvent;
import br.com.event.management.system.core.stored.events.domain.commands.StoredEventCommand;
import br.com.event.management.system.core.stored.events.domain.entities.JsonSerializer;
import br.com.event.management.system.core.stored.events.domain.entities.StoredEvent;
import br.com.event.management.system.core.stored.events.repositories.StoredEventRepository;
import br.com.event.management.system.infrastructure.messaging.rqueue.IRQueueMessageEnqueue;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DomainEventListenerAdapter {

  private final IRQueueMessageEnqueue rQueueMessageEnqueue;

  private final ApplicationOutboxProperties applicationOutboxProperties;

  private final JsonSerializer jsonSerializer;

  private final StoredEventRepository storedEventRepository;

  @EventListener
  public void on(final DomainEvent event) {
    log.info("DomainEvent event received: {}", event);
    this.storedEventRepository.add(StoredEvent.create(new StoredEventCommand(event, event.getOccurredOn(), this.jsonSerializer)));
  }

  @EventListener
  public void on(final PartnerChangedName event) {
    log.info("PartnerChangedName event received: {}", event);
  }

  @EventListener
  public PartnerCreatedIntegrationEvent on(final PartnerCreated event) {
    log.info("PartnerCreated event received: {}", event);
    return PartnerCreatedIntegrationEvent.of(event);
  }

  @EventListener
  public void on(final PartnerCreatedIntegrationEvent event) {
    log.info("PartnerCreatedIntegrationEvent event received: {}", event);
    this.rQueueMessageEnqueue.enqueue(this.applicationOutboxProperties.queue(), event);
  }

}
