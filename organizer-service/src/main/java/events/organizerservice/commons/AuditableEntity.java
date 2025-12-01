package events.organizerservice.commons;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {

  @CreatedDate
  @Column(name = "create_date", updatable = false)
  private LocalDateTime createDate;

  @LastModifiedDate
  @Column(name = "modified_date")
  private LocalDateTime modifiedDate;

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  private UUID createdBy;

}
