package br.com.viniciuspenha.bancodigital.exception;

public class ValidationException extends Exception {

    public ValidationException() {
        super("Erro de validação");
    }

    public ValidationException(String msg) {
        super(msg);
    }
}