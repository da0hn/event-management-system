package br.com.event.management.system.infrastructure.events.database.mappers;

import br.com.event.management.system.core.events.domain.entities.EventSpot;
import br.com.event.management.system.infrastructure.events.database.model.EventSpotModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Stream;

@Mapper(
  unmappedTargetPolicy = ReportingPolicy.ERROR,
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface EventSpotModelMapper {

  @Mappings({
    @Mapping(target = "id", source = "id.value"),
    @Mapping(target = "reserved", source = "reserved"),
    @Mapping(target = "published", source = "published"),
    @Mapping(target = "location", expression = "java(source.getLocation().orElse(null))"),
    @Mapping(target = "section", ignore = true)
  })
  @Named("eventSpotToModel")
  EventSpotModel toModel(EventSpot source);

  @Mapping(target = "takeWhile", ignore = true)
  @Mapping(target = "sorted", ignore = true)
  @Mapping(target = "skip", ignore = true)
  @Mapping(target = "peek", ignore = true)
  @Mapping(target = "limit", ignore = true)
  @Mapping(target = "filter", ignore = true)
  @Mapping(target = "dropWhile", ignore = true)
  @IterableMapping(qualifiedByName = "eventSpotToModel")
  Stream<EventSpotModel> toStreamModel(Set<EventSpot> source);

  @Mappings({
    @Mapping(target = "id.value", source = "id"),
    @Mapping(target = "reserved", source = "reserved"),
    @Mapping(target = "published", source = "published"),
    @Mapping(target = "location", expression = "java(Optional.ofNullable(source.getLocation()))"),
  })
  EventSpot toDomain(EventSpotModel source);

}
