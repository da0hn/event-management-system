package br.com.event.management.system.infrastructure.stored.event.database.repositories;

import br.com.event.management.system.infrastructure.stored.event.database.model.StoredEventModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoredEventModelJpaRepository extends JpaRepository<StoredEventModel, UUID> {

}
