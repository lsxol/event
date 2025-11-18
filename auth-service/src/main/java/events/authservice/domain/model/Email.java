package events.authservice.domain.model;

import jakarta.validation.ValidationException;
import java.util.regex.Pattern;

public record Email(String wartosc) {

  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
      Pattern.CASE_INSENSITIVE);

  public Email {
    if (wartosc == null || !EMAIL_PATTERN.matcher(wartosc).matches()) {
      throw new ValidationException("Niepoprawny adres email");
    }
  }

}
