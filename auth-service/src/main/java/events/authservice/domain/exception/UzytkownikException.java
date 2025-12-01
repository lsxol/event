package events.authservice.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UzytkownikException extends RuntimeException {

  private final KodBledu kodBledu;

  UzytkownikException(String message, KodBledu kodBledu) {
    super(message);
    this.kodBledu = kodBledu;
  }

  @Getter
  @RequiredArgsConstructor
  public enum KodBledu {
    UZYTKOWNIK_NIE_ZNALEZIONY(KategoriaBledu.NOT_FOUND),
    EMAIL_ZAJETY(KategoriaBledu.CONFLICT),
    HASLO_ZA_SLABE(KategoriaBledu.VALIDATION_ERROR),
    EMAIL_ZLY_FORMAT(KategoriaBledu.VALIDATION_ERROR),
    BLAD_TWORZENIA(KategoriaBledu.VALIDATION_ERROR),
    BLAD_LOGOWANIA(KategoriaBledu.VALIDATION_ERROR);
    private final KategoriaBledu kategoria;
  }

  public enum KategoriaBledu {
    NOT_FOUND,
    CONFLICT,
    VALIDATION_ERROR,
    FORBIDDEN,
    SYSTEM_ERROR
  }

}
