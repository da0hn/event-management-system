package br.com.event.management.system.events.domain.entities;

import br.com.event.management.system.common.domain.AggregateRoot;
import br.com.event.management.system.common.domain.valueobjects.CustomerId;
import br.com.event.management.system.common.domain.valueobjects.EventSpotId;
import br.com.event.management.system.common.domain.valueobjects.OrderId;
import br.com.event.management.system.common.domain.valueobjects.OrderStatus;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

@Getter
public class Order extends AggregateRoot<OrderId> {

  private final CustomerId customerId;

  private final BigDecimal amount;

  private final EventSpotId spotId;

  private OrderStatus status;

  public Order(
    final OrderId id,
    final CustomerId customerId,
    final BigDecimal amount,
    final EventSpotId spotId,
    final OrderStatus status
  ) {
    super(id);
    this.customerId = customerId;
    this.amount = amount;
    this.spotId = spotId;
    this.status = status;
  }

  public static Order newInstance(
    final CustomerId customerId,
    final BigDecimal amount,
    final EventSpotId spotId
  ) {
    return new Order(
      OrderId.newInstance(),
      customerId,
      amount,
      spotId,
      OrderStatus.PENDING
    );
  }

  public void pay() {
    this.status = OrderStatus.PAID;
  }

  public void cancel() {
    this.status = OrderStatus.CANCELLED;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, true, AggregateRoot.class);
  }

}
