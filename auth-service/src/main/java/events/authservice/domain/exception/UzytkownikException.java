package events.authservice.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UzytkownikException extends RuntimeException {

  private final ErrorCode errorCode;

  UzytkownikException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  @Getter
  @RequiredArgsConstructor
  public enum ErrorCode {
    UZYTKOWNIK_NIE_ZNALEZIONY(ErrorCategory.NOT_FOUND),
    EMAIL_ZAJETY(ErrorCategory.CONFLICT),
    HASLO_ZA_SLABE(ErrorCategory.VALIDATION_ERROR),
    EMAIL_ZLY_FORMAT(ErrorCategory.VALIDATION_ERROR),
    BLAD_TWORZENIA(ErrorCategory.VALIDATION_ERROR),
    BLAD_LOGOWANIA(ErrorCategory.VALIDATION_ERROR);
    private final ErrorCategory category;
  }

  public enum ErrorCategory {
    NOT_FOUND,
    CONFLICT,
    VALIDATION_ERROR,
    FORBIDDEN,
    SYSTEM_ERROR
  }

}
