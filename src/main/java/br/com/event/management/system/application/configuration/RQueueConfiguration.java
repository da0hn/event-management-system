package br.com.event.management.system.application.configuration;

import br.com.event.management.system.application.configuration.properties.ApplicationOutboxProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sonus21.rqueue.config.SimpleRqueueListenerContainerFactory;
import com.github.sonus21.rqueue.listener.RqueueMessageHandler;
import com.github.sonus21.rqueue.utils.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class RQueueConfiguration {

  private final ApplicationOutboxProperties applicationOutboxProperties;

  public RQueueConfiguration(final ApplicationOutboxProperties applicationOutboxProperties) {
    this.applicationOutboxProperties = applicationOutboxProperties;
  }

  @Bean
  public SimpleRqueueListenerContainerFactory simpleRqueueListenerContainerFactory(
    final RqueueMessageHandler rqueueMessageHandler,
    final MessageConverter jsonMessageConverter
  ) {
    final var simpleRqueueListenerContainerFactory = new SimpleRqueueListenerContainerFactory();
    simpleRqueueListenerContainerFactory.setMaxNumWorkers(this.applicationOutboxProperties.workers());
    simpleRqueueListenerContainerFactory.setPollingInterval(Constants.ONE_MILLI);
    simpleRqueueListenerContainerFactory.setRqueueMessageHandler(rqueueMessageHandler);
    simpleRqueueListenerContainerFactory.setMessageConverterProvider(() -> jsonMessageConverter);
    return simpleRqueueListenerContainerFactory;
  }

  @Bean
  public RqueueMessageHandler rqueueMessageHandler(final MessageConverter jsonMessageConverter) {
    return new RqueueMessageHandler(jsonMessageConverter);
  }

  @Bean
  public MessageConverter jsonMessageConverter(final ObjectMapper objectMapper) {
    final var mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
    mappingJackson2MessageConverter.setObjectMapper(objectMapper);
    return mappingJackson2MessageConverter;
  }

}
