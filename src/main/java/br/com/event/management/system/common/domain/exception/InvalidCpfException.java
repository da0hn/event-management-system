package br.com.event.management.system.common.domain.exception;

import java.io.Serial;

public class InvalidCpfException extends DomainException {

  @Serial
  private static final long serialVersionUID = 12431156040533151L;

  public InvalidCpfException() {
  }

  public InvalidCpfException(final String message) {
    super(message);
  }

  public InvalidCpfException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
