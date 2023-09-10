package br.com.event.management.system.common.domain;

import br.com.event.management.system.common.domain.valueobjects.ValueObject;

@FunctionalInterface
public interface EntityId<T> extends ValueObject {

  T value();

}
