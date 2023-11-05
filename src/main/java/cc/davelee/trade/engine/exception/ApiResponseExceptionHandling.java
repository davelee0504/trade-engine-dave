package cc.davelee.trade.engine.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiResponseExceptionHandling {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException() {
        return new ResponseEntity<>("EXCEPTION!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
