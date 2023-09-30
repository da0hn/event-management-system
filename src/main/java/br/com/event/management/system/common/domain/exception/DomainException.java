package br.com.event.management.system.common.domain.exception;

import java.io.Serial;

public abstract class DomainException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 8366085761389545076L;

  protected DomainException() {
    super();
  }

  protected DomainException(final String message) {
    super(message);
  }

  protected DomainException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
