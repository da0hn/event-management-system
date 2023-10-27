package br.com.event.management.system.core.events.domain.entities;

import br.com.event.management.system.core.common.domain.AggregateRoot;
import br.com.event.management.system.core.common.domain.Entity;
import br.com.event.management.system.core.common.domain.valueobjects.PartnerId;
import br.com.event.management.system.core.events.domain.commands.CreateEventCommand;
import br.com.event.management.system.core.events.domain.commands.CreatePartnerCommand;
import br.com.event.management.system.core.events.domain.commands.InitializeEventCommand;
import br.com.event.management.system.core.events.domain.events.PartnerChangedName;
import br.com.event.management.system.core.events.domain.events.PartnerCreated;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public class Partner extends AggregateRoot<PartnerId> {

  private String name;

  public Partner(final PartnerId id, final String name) {
    super(id);
    this.name = name;
  }

  public static Partner create(final CreatePartnerCommand command) {
    final var partner = new Partner(PartnerId.newInstance(), command.name());
    partner.addEvent(PartnerCreated.of(partner));
    return partner;
  }

  public Event initializeEvent(final InitializeEventCommand command) {
    return Event.create(
      new CreateEventCommand(
        command.name(),
        command.description(),
        command.date(),
        this.getId()
      )
    );
  }

  public void changeName(final String name) {
    this.name = name;
    this.addEvent(PartnerChangedName.of(this));
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, Entity.class);
  }

}
