package events.authservice.commons;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@Table(name = "revision_info")
@RevisionEntity(AuditRevisionListener.class)
@AttributeOverrides({
    @AttributeOverride(name = "timestamp", column = @Column(name = "rev_timestamp")),
    @AttributeOverride(name = "id", column = @Column(name = "revision_id"))
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "REVISION_ID_GENERATOR", sequenceName = "revision_info_s", allocationSize = 1)
class AuditableRevisionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVISION_ID_GENERATOR")
  @RevisionNumber
  private Integer id;

  @RevisionTimestamp
  private Long timestamp;

  @Column(name = "uzytkownik_id")
  private UUID uzytkownikId;

}
