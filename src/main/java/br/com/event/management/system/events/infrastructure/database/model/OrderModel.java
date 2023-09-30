package br.com.event.management.system.events.infrastructure.database.model;

import br.com.event.management.system.common.domain.valueobjects.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class OrderModel implements Serializable {

  @Serial
  private static final long serialVersionUID = 327973303217824989L;

  @Id
  @Column(name = "id")
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerModel customer;

  @Column(name = "amount")
  private BigDecimal amount;

  @ManyToOne
  @JoinColumn(name = "spot_id")
  private EventSpotModel spot;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private OrderStatus status;
}
