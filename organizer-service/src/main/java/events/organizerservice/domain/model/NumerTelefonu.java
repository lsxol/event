package events.organizerservice.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.regex.Pattern;

public record NumerTelefonu(@JsonValue String wartosc) {

  private static final Pattern VALID_PHONE_PATTERN = Pattern.compile("^\\+[1-9]\\d{7,14}$");

  public NumerTelefonu {
    if (wartosc !=null && !VALID_PHONE_PATTERN.matcher(wartosc).matches()) {
      throw new IllegalArgumentException(
          "Nieprawidłowy format numeru telefonu. Oczekiwany format E.164 (np. +48500123456). Otrzymano: " + wartosc);
    }
  }

  public static NumerTelefonu stworz(String numer) {

    String numerPoCzyszczeniu = numer.replaceAll("[^0-9+]", "");

    if (numerPoCzyszczeniu.startsWith("00")) {
      numerPoCzyszczeniu = "+" + numerPoCzyszczeniu.substring(2);
    }

    if (!numerPoCzyszczeniu.startsWith("+") && numerPoCzyszczeniu.length() == 9) {
      numerPoCzyszczeniu = "+48" + numerPoCzyszczeniu;
    }

    if (!numerPoCzyszczeniu.startsWith("+") && numerPoCzyszczeniu.length() > 9) {
      numerPoCzyszczeniu = "+" + numerPoCzyszczeniu;
    }

    return new NumerTelefonu(numerPoCzyszczeniu);
  }

  @Override
  public String toString() {
    return wartosc;
  }

}
