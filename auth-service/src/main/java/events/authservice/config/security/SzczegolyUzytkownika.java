package events.authservice.config.security;

import events.authservice.domain.model.Uzytkownik;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class SzczegolyUzytkownika implements UserDetails {

  private final UUID userId;
  private final String email;
  private final String password;
  private final Collection<? extends GrantedAuthority> authorities;

  public SzczegolyUzytkownika(Uzytkownik uzytkownik) {
    this.userId = uzytkownik.getId().wartosc();
    this.email = uzytkownik.getEmail().wartosc();
    this.password = uzytkownik.getHasloHash().wartosc();
    this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + uzytkownik.getRola()));
  }

  @Override
  public String getUsername() {
    return email;
  }

}
