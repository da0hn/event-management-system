package br.com.event.management.system.infrastructure.events.database.mappers;

import br.com.event.management.system.core.events.domain.entities.SpotReservation;
import br.com.event.management.system.infrastructure.events.database.model.EventSpotReservationModel;
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
  unmappedTargetPolicy = ReportingPolicy.WARN,
  injectionStrategy = InjectionStrategy.CONSTRUCTOR,
  componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class EventSpotReservationModelMapper {

  @Autowired
  protected CustomerModelJpaRepository customerModelJpaRepository;

  @Autowired
  protected EventSpotModelJpaRepository eventSpotModelJpaRepository;

  @Mappings({
    @Mapping(target = "id", source = "id.value"),
    @Mapping(target = "reservationDate", source = "reservationDate"),
    @Mapping(
      target = "customer",
      expression = "java(this.customerModelJpaRepository.findById(source.getCustomerId().value()).orElseThrow())"
    ),
    @Mapping(
      target = "spot",
      expression = "java(this.eventSpotModelJpaRepository.findById(source.getId().value()).orElseThrow())"
    )
  })
  public abstract EventSpotReservationModel toModel(SpotReservation source);

  @Mappings({
    @Mapping(target = "id.value", source = "id"),
    @Mapping(target = "reservationDate", source = "reservationDate"),
    @Mapping(target = "customerId.value", source = "customer.id")
  })
  public abstract SpotReservation toDomain(EventSpotReservationModel source);

}
