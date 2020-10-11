package br.com.viniciuspenha.bancodigital.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PessoaDTO {

    public PessoaDTO(String nome, String sobrenome, String email, LocalDate dataNascimento, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    private Integer id;

    @JsonProperty("nome")
    @NotBlank
    private String nome;

    @JsonProperty("sobrenome")
    @NotBlank
    private String sobrenome;

    @JsonProperty("email")
    @NotBlank
    @Email
    private String email;

    @JsonProperty("dataNascimento")
    @NotBlank
    private LocalDate dataNascimento;

    @JsonProperty("cpf")
    @NotBlank
    @CPF
    private String cpf;

}