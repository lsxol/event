package events.authservice.domain.exception;

final class EmailZlyFormatException extends UzytkownikException {

  public EmailZlyFormatException() {
    super("Email ma zły format.", KodBledu.EMAIL_ZLY_FORMAT);
  }

}
