package br.com.event.management.system.infrastructure.messaging.rqueue;

public interface IRQueueMessageEnqueue {

  void enqueue(
    String queueName,
    Object message
  );

}
