package events.authservice.infrastracture.adapters.driving.api.exception;

import events.authservice.domain.exception.UzytkownikException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // 1. Obsługa wyjątków DOMENOWYCH (Logika biznesowa)
  @ExceptionHandler(UzytkownikException.class)
  public ResponseEntity<ErrorResponse> handleUzytkownikException(UzytkownikException ex) {

    HttpStatus status = switch (ex.getErrorCode().getCategory()) {
      case NOT_FOUND -> HttpStatus.NOT_FOUND;
      case CONFLICT -> HttpStatus.CONFLICT;
      case VALIDATION_ERROR -> HttpStatus.BAD_REQUEST;
      case FORBIDDEN -> HttpStatus.FORBIDDEN;
      default -> HttpStatus.INTERNAL_SERVER_ERROR;
    };

    return ResponseEntity
        .status(status)
        .body(new ErrorResponse(ex.getMessage(), ex.getErrorCode().name()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  // Prosty rekord do zwracania błędów domenowych
  public record ErrorResponse(String message, String code) {}

}
