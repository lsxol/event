package events.organizerservice.domain.exception;

final class UzytkownikNiezalogowanyException extends OrganizatorException {

  public UzytkownikNiezalogowanyException() {
    super("Brak zalogowanego użytkownika", KodBledu.UZYTKOWNIK_NIE_JEST_ZALOGOWANY);
  }

}
