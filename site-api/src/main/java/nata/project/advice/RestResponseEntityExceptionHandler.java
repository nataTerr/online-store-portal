package nata.project.advice;

import nata.project.exception.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = {ItemNotFoundException.class})
    protected ResponseEntity<Object> handleProductNotFoundException(
            ItemNotFoundException ex, WebRequest request) {
        ResponseErrorDto responseErrorDtoOfResponse = new ResponseErrorDto(false, ex.getMessage());
        logger.error(ex.getMessage());
        return handleExceptionInternal(ex, responseErrorDtoOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
