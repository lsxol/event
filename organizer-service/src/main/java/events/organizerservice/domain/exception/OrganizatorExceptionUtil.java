package events.organizerservice.domain.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class OrganizatorExceptionUtil {

  public static OrganizatorException uzytkownikNiezalogowanyException() {
    return new UzytkownikNiezalogowanyException();
  }

  public static OrganizatorException emailZlyFormatException() {
    return new EmailZlyFormatException();
  }

  public static OrganizatorException bledneDaneUzytkownikaException() {
    return new BledneDaneUzytkownikaException();
  }

}
