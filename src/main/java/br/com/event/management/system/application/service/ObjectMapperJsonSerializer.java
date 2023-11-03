package br.com.event.management.system.application.service;

import br.com.event.management.system.core.stored.events.domain.entities.JsonSerializer;
import br.com.event.management.system.core.stored.events.domain.entities.JsonSerializerFailureException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperJsonSerializer implements JsonSerializer {

  private final ObjectMapper objectMapper;

  public ObjectMapperJsonSerializer(final ObjectMapper objectMapper) { this.objectMapper = objectMapper; }

  @Override
  public String toJson(final Object data) throws JsonSerializerFailureException {
    try {
      return this.objectMapper.writeValueAsString(data);
    }
    catch (final Exception e) {
      throw new JsonSerializerFailureException(e.getMessage(), e);
    }
  }

  @Override
  public <T> T fromJson(final String json, final Class<T> clazz) throws JsonSerializerFailureException {
    try {
      return this.objectMapper.convertValue(json, clazz);
    }
    catch (final Exception e) {
      throw new JsonSerializerFailureException(e.getMessage(), e);
    }
  }

}
