package events.authservice.config.security;

import events.authservice.config.security.dto.AuthDto;
import events.authservice.domain.model.RoleUzytkownikowEnum;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthHelper {

  private static UUID SYSTEM_USER_UUID;

  public static AuthDto getAuth() {
    if (!jestZalogowany()) {
      return AuthDto.builder()
          .userId(SYSTEM_USER_UUID)
          .rola(RoleUzytkownikowEnum.ADMIN)
          .build();
    }
    return AuthDto.builder()
        .rola(pobierzRole())
        .userId(pobierzIdUzytkownika())
        .build();
  }

  private static boolean jestZalogowany() {
    var auth = SecurityContextHolder.getContext().getAuthentication();
    return auth != null
        && auth.isAuthenticated()
        && auth.getPrincipal() instanceof SzczegolyUzytkownika;
  }

  private static UUID pobierzIdUzytkownika() {
    return ((SzczegolyUzytkownika) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
  }

  private static RoleUzytkownikowEnum pobierzRole() {
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
        .map(grantedAuthority -> RoleUzytkownikowEnum.valueOf(grantedAuthority.getAuthority().replace("ROLE_", "")))
        .findFirst()
        .orElseThrow();
  }

  @Value("${application.system.user.uuid}")
  public void setSystemUserUuid(String uuid) {
    SYSTEM_USER_UUID = UUID.fromString(uuid);
  }

}
