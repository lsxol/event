package events.authservice.domain.model;

import events.authservice.domain.exception.UzytkownikExceptionUtil;
import java.util.regex.Pattern;

public record HasloPlain(String wartosc) {

  private static final Pattern SILNE_HASLO_PATTERN = Pattern.compile(
      "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$"
  );

  public HasloPlain {
    if (wartosc == null || wartosc.isBlank()) {
      throw UzytkownikExceptionUtil.brakWymaganychDanychDoUtworzeniaUzytkownika();
    }
    if (!SILNE_HASLO_PATTERN.matcher(wartosc).matches()) {
      throw UzytkownikExceptionUtil.hasloZaSlabe();
    }
  }

}
