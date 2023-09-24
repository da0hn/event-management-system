package br.com.event.management.system.events.domain.entities;

import br.com.event.management.system.common.domain.AggregateRoot;
import br.com.event.management.system.common.domain.valueobjects.PartnerId;
import br.com.event.management.system.events.domain.commands.CreateEventCommand;
import br.com.event.management.system.events.domain.commands.CreatePartnerCommand;
import br.com.event.management.system.events.domain.commands.InitializeEventCommand;
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
    return new Partner(PartnerId.newInstance(), command.name());
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
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, AggregateRoot.class);
  }

}
