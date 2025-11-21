package events.authservice.domain.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import events.authservice.domain.exception.UzytkownikException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {

  @Test
  void powinnoStworzycEmail() {
    String emailPrawidlowy = "test@test.test";
    assertDoesNotThrow(() -> new Email(emailPrawidlowy));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "bezmalpy.pl",
      "bez.kropki@gmailcom",
      "@gmail.com",
      "jan@.com",
      "",
      "   ",
      "null"
  })
  void powinnoRzucicBladNiepoprawnegoEmaila(String zlyEmail) {
    assertThrows(UzytkownikException.class, () -> new Email(zlyEmail));
  }

}
