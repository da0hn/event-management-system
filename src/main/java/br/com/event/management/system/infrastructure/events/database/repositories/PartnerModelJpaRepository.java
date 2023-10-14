package br.com.event.management.system.infrastructure.events.database.repositories;

import br.com.event.management.system.infrastructure.events.database.model.PartnerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartnerModelJpaRepository extends JpaRepository<PartnerModel, UUID> {
}
