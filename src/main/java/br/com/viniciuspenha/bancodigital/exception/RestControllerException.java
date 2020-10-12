package br.com.viniciuspenha.bancodigital.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice(basePackages = "br.com.viniciuspenha.bancodigital.controller")
public class RestControllerException {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerException.class);

    private ApiError getApiError(String message, HttpStatus httpStatus, HttpServletRequest req) {
        return new ApiError.ApiErrorBuilder()
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(message)
                .path(req.getRequestURI()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleUnexpectedException(HttpServletRequest req, Throwable ex) {
        LOGGER.error("RestControllerException.handleUnexpectedException - " + ex.getMessage());
        return this.getApiError("Um erro inesperado aconteceu", HttpStatus.INTERNAL_SERVER_ERROR, req);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNullPointerException(HttpServletRequest req, Throwable ex) {
        LOGGER.error("RestControllerException.handleNullPointerException - " + ex.getMessage());
        return this.getApiError("NullPointerException", HttpStatus.BAD_REQUEST, req);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(HttpServletRequest req, Throwable ex) {
        LOGGER.error("RestControllerException.handleNotFoundException - " + ex.getMessage());
        return this.getApiError(ex.getMessage(), HttpStatus.NOT_FOUND, req);
    }

    @ExceptionHandler(UnprocessableEntity.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiError handleUnprocessableEntity(HttpServletRequest req, Throwable ex) {
        LOGGER.error("RestControllerException.handleUnprocessableEntity - " + ex.getMessage());
        return this.getApiError(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, req);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException ex) {
        LOGGER.error("RestControllerException.handleMethodArgumentNotValid - " + ex.getMessage());
        BindingResult bindingResult = ex.getBindingResult();
        List<ApiFieldError> apiFieldErrors = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError ->
                        new ApiFieldError.ApiFieldErrorBuilder()
                                .field(fieldError.getField())
                                .message(fieldError.getDefaultMessage())
                                .build()
                )
                .collect(toList());

        return new ApiError.ApiErrorBuilder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(req.getRequestURI())
                .fieldErrors(apiFieldErrors).build();
    }
}