package br.com.event.management.system.infrastructure.events.database.mappers;

import br.com.event.management.system.core.events.domain.entities.Order;
import br.com.event.management.system.infrastructure.events.database.model.OrderModel;
import br.com.event.management.system.infrastructure.events.database.repositories.CustomerModelJpaRepository;
import br.com.event.management.system.infrastructure.events.database.repositories.EventSpotModelJpaRepository;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
  unmappedTargetPolicy = ReportingPolicy.IGNORE,
  componentModel = MappingConstants.ComponentModel.SPRING,
  injectionStrategy = InjectionStrategy.FIELD,
  uses = {
    CustomerModelMapper.class,
    EventSpotModelMapper.class,
    CustomerModelJpaRepository.class,
    EventSpotModelJpaRepository.class
  }
)
public abstract class OrderModelMapper {

  @Autowired
  protected CustomerModelJpaRepository customerModelJpaRepository;

  @Autowired
  protected EventSpotModelJpaRepository eventSpotModelJpaRepository;

  @Mappings({
    @Mapping(target = "id", source = "id.value"),
    @Mapping(target = "status", source = "status"),
    @Mapping(target = "amount", source = "amount"),
    @Mapping(
      target = "customer",
      expression = "java(this.customerModelJpaRepository.findById(source.getCustomerId().value()).orElseThrow())"
    ),
    @Mapping(
      target = "spot",
      expression = "java(this.eventSpotModelJpaRepository.findById(source.getSpotId().value()).orElseThrow())"
    )
  })
  public abstract OrderModel toModel(Order source);

  @Mappings({
    @Mapping(target = "id.value", source = "id"),
    @Mapping(target = "status", source = "status"),
    @Mapping(target = "amount", source = "amount"),
    @Mapping(target = "customerId.value", source = "customer.id"),
    @Mapping(target = "spotId.value", source = "spot.id")
  })
  public abstract Order toDomain(OrderModel orderModel);

}
