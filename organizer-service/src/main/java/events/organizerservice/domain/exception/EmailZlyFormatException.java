package events.organizerservice.domain.exception;

final class EmailZlyFormatException extends OrganizatorException {

  public EmailZlyFormatException() {
    super("Email ma zły format.", KodBledu.EMAIL_ZLY_FORMAT);
  }

}
