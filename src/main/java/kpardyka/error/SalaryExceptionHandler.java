package kpardyka.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class SalaryExceptionHandler {

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<ErrorData> onHttpClientException(HttpClientErrorException exception, HttpServletRequest request) {
        ErrorData errorData = new ErrorData(request.getRequestURI(), exception.getMessage());
        int statusCode = exception.getRawStatusCode();
        return new ResponseEntity<>(errorData, HttpStatus.valueOf(statusCode));
    }

}
