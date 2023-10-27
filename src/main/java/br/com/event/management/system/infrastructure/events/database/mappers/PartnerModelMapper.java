package br.com.event.management.system.infrastructure.events.database.mappers;

import br.com.event.management.system.core.events.domain.entities.Partner;
import br.com.event.management.system.infrastructure.events.database.model.PartnerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PartnerModelMapper {

  @Mappings(
    {
      @Mapping(target = "id", source = "id.value"),
      @Mapping(target = "name", source = "name")
    }
  )
  PartnerModel toModel(Partner partner);

  @Mappings(
    {
      @Mapping(target = "id.value", source = "id"),
      @Mapping(target = "name", source = "name")
    }
  )
  Partner toDomain(PartnerModel partnerModel);

}
