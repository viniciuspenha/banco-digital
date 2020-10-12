package br.com.viniciuspenha.bancodigital.exception;

public class UnprocessableEntity extends Exception {

    public UnprocessableEntity() {
        super("Unprocessable Entity");
    }

    public UnprocessableEntity(String msg) {
        super(msg);
    }
}