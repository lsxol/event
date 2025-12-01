package events.organizerservice.domain.exception;

final class BledneDaneUzytkownikaException extends OrganizatorException {

  public BledneDaneUzytkownikaException() {
    super("Wprowadzono błędne dane użytkownika", KodBledu.BLEDNE_DANE_UZYTKOWNIKA);
  }

}
