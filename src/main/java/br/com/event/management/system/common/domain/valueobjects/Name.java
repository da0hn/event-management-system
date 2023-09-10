package br.com.event.management.system.common.domain.valueobjects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

public record Name(String value) implements ValueObject {

  public Name {
    Assert.hasText(value, "Name cannot be null or empty");
    Assert.state(value.length() >= 3, "Name cannot be less than 3 characters");
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
