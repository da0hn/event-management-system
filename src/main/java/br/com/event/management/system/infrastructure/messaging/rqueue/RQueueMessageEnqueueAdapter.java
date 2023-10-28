package br.com.event.management.system.infrastructure.messaging.rqueue;

import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RQueueMessageEnqueueAdapter implements IRQueueMessageEnqueue {

  private final RqueueMessageEnqueuer rQueueMessageEnqueuer;

  @Override
  public void enqueue(
    final String queueName,
    final Object message
  ) {
    log.info("Enfileirando mensagem {} na fila {}", message, queueName);
    this.rQueueMessageEnqueuer.enqueue(queueName, message);
  }

}
