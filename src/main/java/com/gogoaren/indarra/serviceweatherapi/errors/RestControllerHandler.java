package com.gogoaren.indarra.serviceweatherapi.errors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Clock;

@ControllerAdvice
@RequiredArgsConstructor
public class RestControllerHandler {

    private final Clock clock;

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> recordNotFoundExceptionHandler(final RecordNotFoundException exception,
                                                                        final HttpServletRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .time(clock.instant())
                .build(), HttpStatus.NOT_FOUND);
    }
}
