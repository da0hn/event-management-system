package br.com.event.management.system.events.domain;

import br.com.event.management.system.common.domain.valueobjects.Name;

public record CreateCustomerCommand(Name name, String cpf) {
}
