package br.com.event.management.system.common.domain.valueobjects;

import br.com.event.management.system.common.domain.exception.InvalidCpfException;
import org.springframework.util.Assert;

public record Cpf(String value) implements ValueObject {

  private static final int CPF_DIGITS = 11;

  public Cpf(final String value) {
    Assert.hasText(value, "Cpf cannot be null or empty");
    Assert.state(value.length() >= CPF_DIGITS, "Invalid Cpf length");
    this.value = value.replaceAll("\\D", "");
    this.validate();
  }

  private void validate() {
    if (this.value.length() != CPF_DIGITS) {
      throw new InvalidCpfException("Cpf must have 11 digits, but has " + this.value.length() + " digits.");
    }

    final var allDigitsEquals = this.value.matches("^\\d(\\d)\\1{10}$");
    if (allDigitsEquals) {
      throw new InvalidCpfException("Cpf must have at least two different digits");
    }

    int sum = 0;
    for (var i = 0; i < 9; i++) {
      sum += Character.getNumericValue(this.value.charAt(i)) * (10 - i);
    }
    int firstDigit = CPF_DIGITS - (sum % CPF_DIGITS);
    if (firstDigit > 9) {
      firstDigit = 0;
    }
    sum = 0;
    for (var i = 0; i < 10; i++) {
      sum += Character.getNumericValue(this.value.charAt(i)) * (11 - i);
    }
    int secondDigit = CPF_DIGITS - (sum % CPF_DIGITS);
    if (secondDigit > 9) {
      secondDigit = 0;
    }
    if (firstDigit != Character.getNumericValue(this.value.charAt(9)) ||
        secondDigit != Character.getNumericValue(this.value.charAt(10))
    ) {
      throw new InvalidCpfException("Cpf is invalid");
    }
  }

}
