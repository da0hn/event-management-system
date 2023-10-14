package br.com.event.management.system.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class TransactionTemplateConfiguration {

  @Bean
  public TransactionTemplate transactionTemplate(final PlatformTransactionManager platformTransactionManager) {
    return new TransactionTemplate(platformTransactionManager);
  }

}
