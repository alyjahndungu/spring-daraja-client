package alyjah.io.daraja.client.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.List;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ExceptionResponse> handleException(WebExchangeBindException e) {
        var errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(new ExceptionResponse(errors));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Mono<ExceptionResponse>> error(BadRequestException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Mono.just(new ExceptionResponse(List.of(e.getMessage()))));
    }


    @ExceptionHandler(GatewayException.class)
    public ResponseEntity<ExceptionResponse> error(GatewayException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(new ExceptionResponse(List.of(e.getMessage())));
    }
}
