package events.organizerservice.config.security;

import events.organizerservice.config.security.dto.AuthDto;
import events.organizerservice.config.security.dto.SimpleAuthDetails;
import events.organizerservice.domain.exception.OrganizatorExceptionUtil;
import events.organizerservice.domain.model.Email;
import events.organizerservice.domain.model.UzytkownikId;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public final class AuthHelper {

  public static AuthDto getAuth() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || !auth.isAuthenticated()) {
      throw OrganizatorExceptionUtil.uzytkownikNiezalogowanyException();
    }
    return new AuthDto(new UzytkownikId(pobierzIdUzytkownika(auth)), new Email(pobierzEmailUzytkownika(auth)));
  }

  private static String pobierzEmailUzytkownika(Authentication auth) {
    Object principal = auth.getPrincipal();
    if (principal instanceof User user) {
      return user.getUsername();
    }
    throw OrganizatorExceptionUtil.uzytkownikNiezalogowanyException();
  }

  private static UUID pobierzIdUzytkownika(Authentication auth) {
    Object details = auth.getDetails();
    if (details instanceof SimpleAuthDetails(UUID wartosc)) {
      return wartosc;
    }
    throw OrganizatorExceptionUtil.uzytkownikNiezalogowanyException();
  }

}
