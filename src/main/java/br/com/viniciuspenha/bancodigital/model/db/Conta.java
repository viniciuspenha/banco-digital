package br.com.viniciuspenha.bancodigital.model.db;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
public class Conta {

    @Id
    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private LocalDate nascimento;
    private String cpf;

    public Conta() {
    }

    public Conta(String nome, String sobrenome, String email, LocalDate nascimento, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.nascimento = nascimento;
        this.cpf = cpf;
    }
}