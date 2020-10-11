package br.com.viniciuspenha.bancodigital.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ApiError {

    private int status;
    private String error;
    private String message;
    private String path;
    private List<ApiFieldError> fieldErrors;
}