package br.com.event.management.system.core.events.application.service;

import java.math.BigDecimal;

public interface PaymentGateway {

  void payment(String token, BigDecimal amount);

}
