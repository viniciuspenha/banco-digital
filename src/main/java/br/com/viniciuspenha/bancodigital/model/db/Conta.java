package br.com.viniciuspenha.bancodigital.model.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "email")
    private String email;

    @Column(name = "nascimento")
    private LocalDate nascimento;

    @Column(name = "cpf")
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