package br.com.event.management.system.infrastructure.outbox;

import br.com.event.management.system.core.common.domain.IntegrationEvent;
import br.com.event.management.system.infrastructure.messaging.rabbitmq.publishers.IntegrationEventPublisher;
import com.github.sonus21.rqueue.annotation.RqueueListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OutboxWorker {

  private final IntegrationEventPublisher integrationEventPublisher;

  @RqueueListener(
    value = "${application.outbox.queue}",
    numRetries = "${application.outbox.number-of-retries}",
    deadLetterQueue = "${application.outbox.dead-letter-queue}",
    concurrency = "${application.outbox.concurrency}"
  )
  public void onIntegrationEvent(final IntegrationEvent<?> integrationEvent) {
    log.info("Integration event received {}", integrationEvent);
    this.integrationEventPublisher.publish(integrationEvent);
  }

  @RqueueListener(value = "${application.outbox.dead-letter-queue}", numRetries = "1", concurrency = "1-3")
  public void onDeadLetterQueueMessage(final IntegrationEvent<?> integrationEvent) {
    log.info("Dead letter queue message received {}", integrationEvent);
  }

}
