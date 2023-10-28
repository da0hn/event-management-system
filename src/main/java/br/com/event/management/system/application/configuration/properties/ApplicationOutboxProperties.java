package br.com.event.management.system.application.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.outbox")
public record ApplicationOutboxProperties(
  String queue,
  String numberOfRetries,
  Integer workers,
  String concurrency,
  String deadLetterQueue
) {
}
