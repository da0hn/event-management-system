package br.com.event.management.system.events.infrastructure.database.repositories;

import br.com.event.management.system.events.infrastructure.database.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderModelJpaRepository extends JpaRepository<OrderModel, UUID> {
}
