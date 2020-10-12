package br.com.viniciuspenha.bancodigital.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagemDTO {

    @JsonProperty("mimeType")
    @NotBlank
    private String mimeType;

    @JsonProperty("extensao")
    @NotBlank
    private String extensao;

    @JsonProperty("imagemByteArray")
    @NotNull
    private byte[] imagemByteArray;
}