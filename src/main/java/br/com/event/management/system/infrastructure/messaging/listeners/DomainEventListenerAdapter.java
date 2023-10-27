package br.com.event.management.system.infrastructure.messaging.listeners;

import br.com.event.management.system.core.events.domain.events.PartnerChangedName;
import br.com.event.management.system.core.events.domain.events.PartnerCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DomainEventListenerAdapter {

  @Async
  @EventListener
  public void on(final PartnerChangedName event) {
    log.info("PartnerChangedName event received: {}", event);
  }

  @Async
  @EventListener
  public void on(final PartnerCreated event) {
    log.info("PartnerCreated event received: {}", event);
  }

}
