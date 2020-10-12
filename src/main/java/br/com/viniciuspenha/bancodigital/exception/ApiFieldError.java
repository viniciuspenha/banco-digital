package br.com.viniciuspenha.bancodigital.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class ApiFieldError {

    private String field;
    private String message;
}