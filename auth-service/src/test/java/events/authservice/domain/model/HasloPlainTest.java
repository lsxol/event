package events.authservice.domain.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import events.authservice.domain.exception.UzytkownikException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HasloPlainTest {

  @Test
  void powinnoUtworzycHaslo() {
    String silneHaslo = "SilneHaslo123123!";
    assertDoesNotThrow(() -> new HasloPlain(silneHaslo));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "kr",
      "brakduzejlitery1!",
      "BrakCyfry!",
      "BrakZnakuSpecjalnego1",
      "",
      "       "
  })
  void powinnoRzucicBladSlabegoHasla() {
    String slabeHaslo = "21";
    assertThrows(UzytkownikException.class, () -> new HasloPlain(slabeHaslo));
  }

}
