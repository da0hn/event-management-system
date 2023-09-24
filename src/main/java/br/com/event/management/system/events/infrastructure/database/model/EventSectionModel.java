package br.com.event.management.system.events.infrastructure.database.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_section")
public class EventSectionModel implements Serializable {

  @Serial
  private static final long serialVersionUID = 24429610949696992L;

  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "published")
  private Boolean published;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "total_spots")
  private Long totalSpots;

  @Column(name = "total_spots_reserved")
  private Long totalSpotsReserved;

  @ManyToOne
  @JoinColumn(name = "event_id")
  private EventModel event;

  @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private Set<EventSpotModel> spots;

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final EventSectionModel that = (EventSectionModel) o;

    return this.id.equals(that.id);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " " + ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
