package br.com.event.management.system.infrastructure.events.database.repositories;

import br.com.event.management.system.infrastructure.events.database.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventModelJpaRepository extends JpaRepository<EventModel, UUID> {
}
