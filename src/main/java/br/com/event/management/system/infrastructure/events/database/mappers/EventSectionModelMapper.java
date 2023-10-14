package br.com.event.management.system.infrastructure.events.database.mappers;

import br.com.event.management.system.core.events.domain.entities.EventSection;
import br.com.event.management.system.infrastructure.events.database.model.EventSectionModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(
  unmappedTargetPolicy = ReportingPolicy.ERROR,
  componentModel = MappingConstants.ComponentModel.SPRING,
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  uses = { EventSpotModelMapper.class }
)
public abstract class EventSectionModelMapper {

  @Autowired
  protected EventSpotModelMapper eventSpotModelMapper;

  @Mappings({
    @Mapping(target = "id", source = "source.id.value"),
    @Mapping(target = "name", source = "source.name"),
    @Mapping(target = "description", expression = "java(source.getDescription().orElse(null))"),
    @Mapping(target = "published", source = "source.published"),
    @Mapping(target = "price", source = "source.price"),
    @Mapping(target = "totalSpots", source = "source.totalSpots"),
    @Mapping(target = "totalSpotsReserved", source = "source.totalSpotsReserved"),
    @Mapping(target = "spots", ignore = true),
    @Mapping(target = "event", ignore = true),
  })
  @Named("eventSectionToModel")
  public abstract EventSectionModel toModel(EventSection source);

  @Mappings({
    @Mapping(target = "id.value", source = "id"),
    @Mapping(target = "name", source = "name"),
    @Mapping(target = "description", expression = "java(Optional.ofNullable(source.getDescription()))"),
    @Mapping(target = "published", source = "published"),
    @Mapping(target = "price", source = "price"),
    @Mapping(target = "totalSpots", source = "totalSpots"),
    @Mapping(target = "totalSpotsReserved", source = "totalSpotsReserved"),
    @Mapping(target = "spots", source = "spots"),
  })
  @Named("eventSectionToDomain")
  public abstract EventSection toDomain(EventSectionModel source);

  @Named("eventSectionToCollectionDomain")
  @IterableMapping(qualifiedByName = "eventSectionToDomain")
  public abstract Set<EventSection> toCollectionDomain(Set<EventSectionModel> source);

  @Named("eventSectionToCollectionModel")
  @IterableMapping(qualifiedByName = "eventSectionToModel")
  public abstract Set<EventSectionModel> toCollectionModel(Set<EventSection> source);

  @Mapping(target = "takeWhile", ignore = true)
  @Mapping(target = "sorted", ignore = true)
  @Mapping(target = "skip", ignore = true)
  @Mapping(target = "peek", ignore = true)
  @Mapping(target = "limit", ignore = true)
  @Mapping(target = "filter", ignore = true)
  @Mapping(target = "dropWhile", ignore = true)
  @IterableMapping(qualifiedByName = "eventSectionToModel")
  public abstract Stream<EventSectionModel> toStreamModel(Set<EventSection> source);

  @AfterMapping
  protected void fillCollectionEventSpotModel(final EventSection eventSection, @MappingTarget final EventSectionModel model) {
    model.setSpots(
      this.eventSpotModelMapper.toStreamModel(eventSection.getSpots())
        .peek(spot -> spot.setSection(model))
        .collect(Collectors.toSet())
    );
  }

}
