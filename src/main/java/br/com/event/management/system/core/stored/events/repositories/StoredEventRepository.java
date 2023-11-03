package br.com.event.management.system.core.stored.events.repositories;

import br.com.event.management.system.core.common.domain.EntityId;
import br.com.event.management.system.core.common.domain.exception.DomainEntityNotFoundException;
import br.com.event.management.system.core.common.domain.valueobjects.StoredEventId;
import br.com.event.management.system.core.stored.events.domain.entities.StoredEvent;
import br.com.event.management.system.infrastructure.stored.event.database.model.StoredEventModel;

import java.util.List;
import java.util.UUID;

public interface StoredEventRepository {

  List<StoredEvent> allBetween(StoredEventId lowStoredEventId, StoredEventId highStoredEventId);

  List<StoredEvent> allSince(StoredEventId storedEventId);

  StoredEvent add(StoredEvent domainEvent);

  StoredEventModel findByIdOrElseThrow(EntityId<UUID> id) throws DomainEntityNotFoundException;

}
