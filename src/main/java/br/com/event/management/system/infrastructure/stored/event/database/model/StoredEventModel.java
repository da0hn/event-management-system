package br.com.event.management.system.infrastructure.stored.event.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stored_event")
public class StoredEventModel implements Serializable {

  @Serial
  private static final long serialVersionUID = -238641452448001594L;

  @Id
  @Column(name = "id", nullable = false, length = 36)
  private UUID id;

  @NotNull
  @Column(name = "body", nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private String body;

  @NotNull
  @Column(name = "occurred_on", nullable = false)
  private LocalDateTime occurredOn;

  @NotNull
  @Size(max = 50)
  @Column(name = "type_name", nullable = false, length = 50)
  private String typeName;

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof final StoredEventModel that)) return false;

    return this.id.equals(that.id);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
      .append("id", this.id)
      .append("body", this.body)
      .append("occurredOn", this.occurredOn)
      .append("typeName", this.typeName)
      .toString();
  }

}
