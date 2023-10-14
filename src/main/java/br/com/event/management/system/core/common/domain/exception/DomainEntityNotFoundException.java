package br.com.event.management.system.core.common.domain.exception;

import java.io.Serial;

public class DomainEntityNotFoundException extends DomainException {

  @Serial
  private static final long serialVersionUID = -3491933779640556851L;

  public DomainEntityNotFoundException() {
    super();
  }

  public DomainEntityNotFoundException(final String message) {
    super(message);
  }

  public DomainEntityNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
