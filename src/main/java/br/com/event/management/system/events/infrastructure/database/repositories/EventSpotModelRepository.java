package br.com.event.management.system.events.infrastructure.database.repositories;

import br.com.event.management.system.events.infrastructure.database.model.EventSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventSpotModelRepository extends JpaRepository<EventSpotModel, UUID> {
}
