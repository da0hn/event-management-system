package br.com.event.management.system.events.domain.commands;

public record CreateCustomerCommand(String name, String cpf) {
}
