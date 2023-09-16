package br.com.event.management.system.events.infrastructure.database.repositories;

import br.com.event.management.system.events.infrastructure.database.model.PartnerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartnerModelJpaRepository extends JpaRepository<PartnerModel, UUID> {
}
