package br.com.viniciuspenha.bancodigital.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO {

    @JsonProperty("cep")
    @NotNull
    @Pattern(regexp = "\\d{5}-\\d{3}")
    private String cep;

    @JsonProperty("rua")
    @NotBlank
    private String rua;

    @JsonProperty("bairro")
    @NotBlank
    private String bairro;

    @JsonProperty("complemento")
    private String complemento;

    @JsonProperty("cidade")
    @NotBlank
    private String cidade;

    @JsonProperty("estado")
    @NotBlank
    private String estado;

}