package br.com.event.management.system.events.infrastructure.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "partner")
public class PartnerModel implements Serializable {

  @Serial
  private static final long serialVersionUID = 5079629680000696006L;

  @Id
  @Column(nullable = false, length = 36)
  private UUID id;

  @Column(name = "name")
  private String name;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " " + ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
