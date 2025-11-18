package events.authservice.domain.exception;

import java.util.function.Supplier;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class UzytkownikExceptionUtil {

  public final static String EMAIL = "e-mail";

  public static Supplier<UzytkownikException> nieZnalezionoUzytkownikaException(String zmienna,
      String identyfikator) {
    return () -> new NieZnalezionoUzytkownikaException(zmienna, identyfikator);
  }

  public static UzytkownikException brakWymaganychDanychDoUtworzeniaUzytkownika() {
    return new NieMoznaUtworzycUzytkownikaException();
  }

  public static UzytkownikException uzytkownikOPodanymMejluIstniejeException() {
    return new UzytkownikOPodanymMejluIstniejeException();
  }

  public static UzytkownikException hasloZaSlabe() {
    return new HasloZaSlabeException();
  }

}
