package br.com.event.management.system.events.domain.entities;

import br.com.event.management.system.common.domain.AggregateRoot;
import br.com.event.management.system.common.domain.Entity;
import br.com.event.management.system.common.domain.valueobjects.CustomerId;
import br.com.event.management.system.common.domain.valueobjects.EventSpotId;
import br.com.event.management.system.events.domain.commands.CreateSpotReservationCommand;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;

@Getter
public class SpotReservation extends AggregateRoot<EventSpotId> {

  private LocalDate reservationDate;

  private CustomerId customerId;

  public SpotReservation(final EventSpotId id, final LocalDate reservationDate, final CustomerId customerId) {
    super(id);
    this.reservationDate = reservationDate;
    this.customerId = customerId;
  }

  public static SpotReservation create(final CreateSpotReservationCommand command) {
    return new SpotReservation(
      command.spotId(),
      LocalDate.now(),
      command.customerId()
    );
  }

  public void changeReservation(final CustomerId customerId) {
    this.customerId = customerId;
    this.reservationDate = LocalDate.now();
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, true, Entity.class);
  }

}
