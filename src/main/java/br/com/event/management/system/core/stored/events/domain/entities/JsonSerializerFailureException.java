package br.com.event.management.system.core.stored.events.domain.entities;

import java.io.Serial;

public class JsonSerializerFailureException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -5126819395126314942L;

  public JsonSerializerFailureException() {
  }

  public JsonSerializerFailureException(final String message) {
    super(message);
  }

  public JsonSerializerFailureException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
