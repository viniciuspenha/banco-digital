package br.com.viniciuspenha.bancodigital.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiFieldError {

    private String field;
    private String message;
}