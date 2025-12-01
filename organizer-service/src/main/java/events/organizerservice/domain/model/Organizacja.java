package events.organizerservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Organizacja {

  private final OrganizacjaId id;
  private final String nazwa;
  
}
