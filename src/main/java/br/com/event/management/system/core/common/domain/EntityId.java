package br.com.event.management.system.core.common.domain;

import br.com.event.management.system.core.common.domain.valueobjects.ValueObject;

@FunctionalInterface
public interface EntityId<T> extends ValueObject {

  T value();

}
