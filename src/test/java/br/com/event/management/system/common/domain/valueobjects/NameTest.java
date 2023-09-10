package br.com.event.management.system.common.domain.valueobjects;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
public class NameTest {

  @Test
  @DisplayName("Should create a valid name")
  void test1() {
    final var name = new Name("A Name");
    Assertions.assertThat(name)
      .extracting(Name::value)
      .isEqualTo("A Name");
  }

  @Test
  @DisplayName("Should not create name with empty value")
  void test2() {
    Assertions.assertThatThrownBy(() -> new Name("   "))
      .hasMessage("Name cannot be null or empty");
  }

  @Test
  @DisplayName("Should not create name with null value")
  void test3() {
    Assertions.assertThatThrownBy(() -> new Name(null))
      .hasMessage("Name cannot be null or empty");
  }

  @Test
  @DisplayName("Should not create name with less than 3 characters")
  void test4() {
    Assertions.assertThatThrownBy(() -> new Name("ab"))
      .hasMessage("Name cannot be less than 3 characters");
  }

}
