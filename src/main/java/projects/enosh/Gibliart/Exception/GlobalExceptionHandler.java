package projects.enosh.Gibliart.Exception;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleGlobalExceptions(RuntimeException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Error occured")
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignClientException(FeignException e) {
        HttpStatus status = HttpStatus.valueOf(e.status());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("External api error: "+ e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        LOGGER.warn(errorResponse.getMessage().);
        return new ResponseEntity<>(errorResponse, status);
    }
}
