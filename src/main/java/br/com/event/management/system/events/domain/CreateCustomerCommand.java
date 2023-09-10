package br.com.event.management.system.events.domain;

public record CreateCustomerCommand(String name, String cpf) {
}
