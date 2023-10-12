package br.com.event.management.system.events.application.service.impl;

import br.com.event.management.system.events.application.service.PaymentGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

  @Override
  public void payment(final String token, final BigDecimal amount) {

  }

}
