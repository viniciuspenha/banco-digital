package br.com.viniciuspenha.bancodigital.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO {

    @JsonProperty("cep")
    @NotBlank
    @Size(min = 8, max = 9)
    private String cep;

    @JsonProperty("rua")
    @NotBlank
    private String rua;

    @JsonProperty("bairro")
    @NotBlank
    private String bairro;

    @JsonProperty("complemento")
    @NotBlank
    private String complemento;

    @JsonProperty("cidade")
    @NotBlank
    private String cidade;

    @JsonProperty("estado")
    @NotBlank
    private String estado;

}