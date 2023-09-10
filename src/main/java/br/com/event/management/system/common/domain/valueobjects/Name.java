package br.com.event.management.system.common.domain.valueobjects;

import org.springframework.util.Assert;

public record Name(String value) implements ValueObject {

  public Name {
    Assert.hasText(value, "Name cannot be null or empty");
    Assert.state(value.length() >= 3, "Name cannot be less than 3 characters");
  }

}
