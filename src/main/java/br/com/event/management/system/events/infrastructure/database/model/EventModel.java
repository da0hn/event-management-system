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
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
public class EventModel implements Serializable {

  @Serial
  private static final long serialVersionUID = 4098387170632437629L;

  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "total_spots_reserved")
  private Long totalSpotsReserved;

  @Column(name = "published")
  private Boolean published;

  @Column(name = "total_spots")
  private Long totalSpots;

  @Column(name = "date")
  private LocalDate date;

  @ManyToOne
  @JoinColumn(name = "partner_id")
  private PartnerModel partner;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private Set<EventSectionModel> sections;

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final EventModel that = (EventModel) o;

    return this.id.equals(that.id);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " " + ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
