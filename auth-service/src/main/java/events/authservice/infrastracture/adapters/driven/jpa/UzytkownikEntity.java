package events.authservice.infrastracture.adapters.driven.jpa;

import events.authservice.commons.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "uzytkownik")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UzytkownikEntity extends AuditableEntity {

  @Id
  private UUID id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String passwordHash;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RoleJPA role;

  public enum RoleJPA {
    KLIENT, ADMIN, ORGANIZATOR
  }

}
