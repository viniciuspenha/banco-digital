package br.com.viniciuspenha.bancodigital.exception;

public class EmailDuplicadoException extends Exception {
    public EmailDuplicadoException() {
        super("Este e-mail já esta cadastrado em nossa base, favor utilizar outro.");
    }
}