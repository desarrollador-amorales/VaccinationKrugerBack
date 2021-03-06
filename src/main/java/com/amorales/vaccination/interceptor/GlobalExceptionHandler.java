package com.amorales.vaccination.interceptor;

import com.amorales.vaccination.exception.AuthException;
import com.amorales.vaccination.exception.GeneralException;
import com.amorales.vaccination.exception.RequestValidationException;
import com.amorales.vaccination.exception.TokenException;
import com.amorales.vaccination.pojos.errors.ApiError;
import com.amorales.vaccination.pojos.errors.ApiFieldError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Filter of exception into request
 *
 * @author amorales
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * @param requestValidationException Throw Error
     * @param webRequest   Request
     * @return Final custom exception
     */
    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<Object> handleRequestValidationException(
            RequestValidationException requestValidationException, WebRequest webRequest){
        log.debug("Exception for return ApiFieldError: {}", requestValidationException);
        ApiFieldError listError = requestValidationException.getApiListError();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listError);
    }

    /**
     * @param requestValidationException Throw Error
     * @param webRequest   Request
     * @return Final custom exception
     */
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> handleGeneralException(GeneralException requestValidationException, WebRequest webRequest){
        log.debug("Exception for return handleGeneralException: {}", requestValidationException);
        ApiError error = requestValidationException.getApiError();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    /**
     * @param authException Throw Error
     * @param webRequest   Request
     * @return Final custom exception
     */
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Object> handleAuthException(AuthException authException, WebRequest webRequest){
        log.debug("Exception for return handleAuthException: {}", authException);
        ApiError error = authException.getApiError();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    /**
     * @param tokenException Throw Error
     * @param webRequest   Request
     * @return Final custom exception
     */
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Object> handleTokenException(TokenException tokenException, WebRequest webRequest){
        log.debug("Exception for return tokenException: {}", tokenException);
        ApiError error = tokenException.getApiError();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    /**
     * @param exception Throw Error
     * @param webRequest   Request
     * @return Final custom exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception, WebRequest webRequest){
        log.debug("Exception for return handleGlobalException: {}", exception);
        ApiError error = new ApiError();
        error.setCode("03");
        error.setMessage(exception.getMessage());
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
