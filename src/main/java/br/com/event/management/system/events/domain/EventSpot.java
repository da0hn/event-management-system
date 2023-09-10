package br.com.event.management.system.events.domain;

import br.com.event.management.system.common.domain.Entity;
import br.com.event.management.system.common.domain.valueobjects.EventSpotId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Optional;

public class EventSpot extends Entity<EventSpotId> {

  private final Optional<String> location;

  private final boolean reserved;

  private final boolean published;

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

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, Entity.class);
  }

}
