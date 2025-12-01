package events.organizerservice.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class OrganizatorException extends RuntimeException {

  private final KodBledu kodBledu;

  OrganizatorException(String message, KodBledu kodBledu) {
    super(message);
    this.kodBledu = kodBledu;
  }

  @Getter
  @RequiredArgsConstructor
  public enum KodBledu {
    UZYTKOWNIK_NIE_JEST_ZALOGOWANY(KategoriaBledow.FORBIDDEN),
    BLEDNE_DANE_UZYTKOWNIKA(KategoriaBledow.VALIDATION_ERROR),
    EMAIL_ZLY_FORMAT(KategoriaBledow.VALIDATION_ERROR);
    private final KategoriaBledow kategoria;
  }

  public enum KategoriaBledow {
    NOT_FOUND,
    CONFLICT,
    VALIDATION_ERROR,
    FORBIDDEN,
    SYSTEM_ERROR
  }

}
