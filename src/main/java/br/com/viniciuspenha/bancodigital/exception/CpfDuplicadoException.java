package br.com.viniciuspenha.bancodigital.exception;

public class CpfDuplicadoException extends Exception {
    public CpfDuplicadoException() {
        super("Este cpf já esta cadastrado em nossa base, favor utilizar outro.");
    }
}