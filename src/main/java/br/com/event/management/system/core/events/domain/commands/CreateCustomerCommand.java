package br.com.event.management.system.core.events.domain.commands;

public record CreateCustomerCommand(String name, String cpf) {
}
