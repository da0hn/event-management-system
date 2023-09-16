package br.com.event.management.system.events.infrastructure.database.repository;

import br.com.event.management.system.events.infrastructure.database.PartnerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartnerModelRepository extends JpaRepository<PartnerModel, UUID> {
}
