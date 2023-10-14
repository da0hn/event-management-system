package br.com.event.management.system.infrastructure.events.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_spot_reservation")
public class EventSpotReservationModel implements Serializable {

  @Serial
  private static final long serialVersionUID = -4773889619712162848L;

  @Id
  @Column(name = "spot_id")
  private UUID id;

  @MapsId
  @ManyToOne
  @JoinColumn(name = "spot_id")
  private EventSpotModel spot;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerModel customer;

  @Column(name = "reservation_date")
  private LocalDate reservationDate;

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final EventSpotReservationModel that = (EventSpotReservationModel) o;

    return this.id.equals(that.id);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " " + ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
