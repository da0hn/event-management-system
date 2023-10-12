package br.com.event.management.system.events.application.service;

import java.math.BigDecimal;

public interface PaymentGateway {

  void payment(String token, BigDecimal amount);

}
