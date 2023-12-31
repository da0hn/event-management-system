package br.com.event.management.system.core.events.domain.entities;

import br.com.event.management.system.core.common.domain.Entity;
import br.com.event.management.system.core.common.domain.valueobjects.EventSpotId;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Optional;

@Getter
public class EventSpot extends Entity<EventSpotId> {

  private boolean reserved;

  private boolean published;

  private Optional<String> location;

  public EventSpot(final EventSpotId id, final Optional<String> location, final boolean reserved, final boolean published) {
    super(id);
    this.location = location;
    this.reserved = reserved;
    this.published = published;
  }

  public static EventSpot create() {
    return new EventSpot(
      EventSpotId.newInstance(),
      Optional.empty(),
      false,
      false
    );
  }

  public void changeLocation(final String location) {
    this.location = Optional.ofNullable(location);
  }

  public void publish() {
    this.published = true;
  }

  public void unPublish() {
    this.published = false;
  }

  public void markAsReserved() {
    this.reserved = true;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, Entity.class);
  }

}
