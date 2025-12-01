package events.organizerservice.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import events.organizerservice.domain.exception.OrganizatorExceptionUtil;
import java.util.regex.Pattern;

public record Email(@JsonValue String wartosc) {

  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
      Pattern.CASE_INSENSITIVE);

  public Email {
    if (wartosc == null || !EMAIL_PATTERN.matcher(wartosc).matches()) {
      throw OrganizatorExceptionUtil.emailZlyFormatException();
    }
  }

  @Override
  public String toString() {
    return wartosc;
  }

}
