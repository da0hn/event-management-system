package br.com.event.management.system.core.stored.events.domain.entities;

public interface JsonSerializer {

  String toJson(Object data) throws JsonSerializerFailureException;

  <T> T fromJson(String json, Class<T> clazz) throws JsonSerializerFailureException;

}
