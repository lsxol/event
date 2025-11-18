package events.authservice.config.security.dto;

import events.authservice.domain.model.RoleUzytkownikowEnum;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {

  private UUID userId;
  private RoleUzytkownikowEnum rola;

}
