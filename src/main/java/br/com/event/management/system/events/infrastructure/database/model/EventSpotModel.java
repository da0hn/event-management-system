package br.com.event.management.system.events.infrastructure.database.model;

import br.com.event.management.system.events.domain.EventSection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_spot")
public class EventSpotModel implements Serializable {

  @Serial
  private static final long serialVersionUID = -8236873457668449577L;

  @Id
  @Column(name = "id", length = 36, nullable = false)
  private UUID id;

  @Column(name = "reserved", nullable = false)
  private Boolean reserved;

  @Column(name = "published", nullable = false)
  private Boolean published;

  @Column(name = "location")
  private String location;

  @ManyToOne
  @JoinColumn(name = "section_id")
  private EventSectionModel section;

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final EventSpotModel that = (EventSpotModel) o;

    return this.id.equals(that.id);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " " + ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
