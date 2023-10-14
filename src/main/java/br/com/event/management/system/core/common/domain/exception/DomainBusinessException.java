package br.com.event.management.system.core.common.domain.exception;

import java.io.Serial;

public class DomainBusinessException extends DomainException {

  @Serial
  private static final long serialVersionUID = -7976125947305252532L;

  public DomainBusinessException() {
    super();
  }

  public DomainBusinessException(final String message) {
    super(message);
  }

  public DomainBusinessException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
