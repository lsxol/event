package events.authservice.config.jpa;

import events.authservice.config.security.AuthHelper;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditorAware")
public class EntityAuditorAware implements AuditorAware<UUID> {

  @Override
  public Optional<UUID> getCurrentAuditor() {
    return Optional.ofNullable(AuthHelper.getAuth().getUserId());
  }

}
