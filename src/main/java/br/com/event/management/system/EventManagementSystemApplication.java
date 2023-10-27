package br.com.event.management.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
public class EventManagementSystemApplication {

  public static void main(final String[] args) {
    SpringApplication.run(EventManagementSystemApplication.class, args);
  }

}
