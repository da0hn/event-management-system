package br.com.event.management.system.email.service.infrastructure.messaging.rabbitmq.listeners

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class ConsumerService {

  companion object {
    @JvmStatic
    private val log = LoggerFactory.getLogger(ConsumerService::class.java)
  }

  @RabbitListener(queues = ["\${application.rabbitmq.emails.queue}"])
  fun handle(event: Any) {
    log.info("Received event: $event")
  }

}
