package br.com.event.management.system.infrastructure.stored.event.database.mappers;

import br.com.event.management.system.core.stored.events.domain.entities.StoredEvent;
import br.com.event.management.system.infrastructure.stored.event.database.model.StoredEventModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StoredEventModelMapper {

  @Mappings({
    @Mapping(target = "id", source = "id.value"),
    @Mapping(target = "body", source = "body"),
    @Mapping(target = "occurredOn", source = "occurredOn"),
    @Mapping(target = "typeName", source = "typeName"),
  })
  StoredEventModel toModel(StoredEvent storedEvent);

  @Mappings({
    @Mapping(target = "id.value", source = "id"),
    @Mapping(target = "body", source = "body"),
    @Mapping(target = "occurredOn", source = "occurredOn"),
    @Mapping(target = "typeName", source = "typeName"),
  })
  StoredEvent toDomain(StoredEventModel storedEventModel);

}
