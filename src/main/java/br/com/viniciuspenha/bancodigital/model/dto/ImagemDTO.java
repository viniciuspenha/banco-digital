package br.com.viniciuspenha.bancodigital.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ImagemDTO {

    @JsonProperty("mimeType")
    @NotBlank
    private String mimeType;

    @JsonProperty("imagemByteArray")
    @NotBlank
    private byte[] imagemByteArray;
}