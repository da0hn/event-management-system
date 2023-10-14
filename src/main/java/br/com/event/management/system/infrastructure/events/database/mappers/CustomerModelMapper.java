package br.com.event.management.system.infrastructure.events.database.mappers;

import br.com.event.management.system.core.events.domain.entities.Customer;
import br.com.event.management.system.infrastructure.events.database.model.CustomerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerModelMapper {

  @Mappings({
    @Mapping(target = "id", source = "id.value"),
    @Mapping(target = "name", source = "name"),
    @Mapping(target = "cpf", source = "cpf.value")
  })
  CustomerModel toModel(Customer customer);

  @Mappings({
    @Mapping(target = "id.value", source = "id"),
    @Mapping(target = "name", source = "name"),
    @Mapping(target = "cpf.value", source = "cpf")
  })
  Customer toDomain(CustomerModel customerModel);

}
