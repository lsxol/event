package events.authservice.domain.exception;

import java.util.function.Supplier;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class UzytkownikExceptionUtil {

  public static Supplier<UzytkownikException> nieZnalezionoUzytkownikaException() {
    return NieZnalezionoUzytkownikaException::new;
  }

  public static UzytkownikException brakWymaganychDanychDoUtworzeniaUzytkownika() {
    return new NieMoznaUtworzycUzytkownikaException();
  }

  public static UzytkownikException brakWymaganychDanychDoZalogowaniaUzytkownika() {
    return new NieMoznaZalogowacUzytkownikaException();
  }

  public static UzytkownikException uzytkownikOPodanymMejluIstniejeException() {
    return new UzytkownikOPodanymMejluIstniejeException();
  }

  public static UzytkownikException hasloZaSlabe() {
    return new HasloZaSlabeException();
  }

  public static UzytkownikException emailZlyFormat() {
    return new EmailZlyFormatException();
  }

}
