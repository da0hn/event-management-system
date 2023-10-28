package br.com.event.management.system.infrastructure.messaging.rabbitmq.publishers;

import br.com.event.management.system.core.common.domain.IntegrationEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class IntegrationEventPublisher {

  private final RabbitTemplate rabbitTemplate;

  @Value("${application.rabbitmq.emails.exchange}")
  private final String exchange;

  public void publish(final IntegrationEvent<?> integrationEvent) {
    final var routingKey = integrationEvent.getEventName();
    log.info(
      "Publishing integration event {} in exchange={}, routingKey={}",
      integrationEvent,
      this.exchange,
      routingKey
    );
    this.rabbitTemplate.convertAndSend(this.exchange, routingKey, integrationEvent);
  }

}
