package br.com.event.management.system.infrastructure.events.database.mappers;

import br.com.event.management.system.infrastructure.events.database.repositories.PartnerModelJpaRepository;
import br.com.event.management.system.core.events.domain.entities.Event;
import br.com.event.management.system.infrastructure.events.database.model.EventModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(
  unmappedTargetPolicy = ReportingPolicy.WARN,
  componentModel = MappingConstants.ComponentModel.SPRING,
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  uses = { PartnerModelMapper.class, EventSectionModelMapper.class })
public abstract class EventModelMapper {

  @Autowired
  protected PartnerModelJpaRepository partnerModelJpaRepository;

  @Autowired
  protected EventSectionModelMapper eventSectionModelMapper;

  @Mappings({
    @Mapping(target = "id", source = "id.value"),
    @Mapping(target = "name", source = "name"),
    @Mapping(target = "description", expression = "java(source.getDescription().orElse(null))"),
    @Mapping(target = "date", source = "date"),
    @Mapping(target = "published", source = "published"),
    @Mapping(target = "totalSpots", source = "totalSpots"),
    @Mapping(target = "totalSpotsReserved", source = "totalSpotsReserved"),
    @Mapping(
      target = "partner",
      expression = "java(this.partnerModelJpaRepository.findById(source.getPartnerId().value()).orElseThrow())"
    ),
    @Mapping(target = "sections", ignore = true),
  })
  public abstract EventModel toModel(Event source);

  @Mappings({
    @Mapping(target = "id.value", source = "id"),
    @Mapping(target = "name", source = "name"),
    @Mapping(target = "description", expression = "java(Optional.ofNullable(source.getDescription()))"),
    @Mapping(target = "date", source = "date"),
    @Mapping(target = "published", source = "published"),
    @Mapping(target = "totalSpots", source = "totalSpots"),
    @Mapping(target = "totalSpotsReserved", source = "totalSpotsReserved"),
    @Mapping(target = "partnerId.value", source = "partner.id"),
    @Mapping(target = "sections", source = "sections", qualifiedByName = "eventSectionToCollectionDomain")
  })
  public abstract Event toDomain(EventModel source);

  @AfterMapping
  protected void fillCollectionSectionModel(final Event event, @MappingTarget final EventModel model) {
    model.setSections(
      this.eventSectionModelMapper.toStreamModel(event.getSections())
        .peek(section -> section.setEvent(model))
        .collect(Collectors.toSet())
    );
  }

}
