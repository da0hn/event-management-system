package br.com.event.management.system.events.infrastructure.database.repositories;

import br.com.event.management.system.events.infrastructure.database.model.EventSpotReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventSpotReservationModelJpaRepository extends JpaRepository<EventSpotReservationModel, UUID> {
}
