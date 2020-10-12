package br.com.viniciuspenha.bancodigital.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosPessoaisDTO {

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dataNascimento;

    @JsonProperty("cpf")
    @NotBlank
    @CPF
    private String cpf;

}