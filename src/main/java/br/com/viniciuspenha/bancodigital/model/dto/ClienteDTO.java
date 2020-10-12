package br.com.viniciuspenha.bancodigital.model.dto;


import br.com.viniciuspenha.bancodigital.model.db.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("cliente")
    private DadosPessoaisDTO dadosPessoaisDTO;

    @JsonProperty("endereco")
    private EnderecoDTO enderecoDTO;

    @JsonProperty("urlCpfFoto")
    private String urlCpfFoto;

    @JsonProperty("dataCriacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime dataCriacao;

    @JsonProperty("dataAtualizacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime dataAtualizacao;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();

        this.dadosPessoaisDTO = new DadosPessoaisDTO(
                cliente.getNome(),
                cliente.getSobrenome(),
                cliente.getEmail(),
                cliente.getDataNascimento(),
                cliente.getCpf()
        );

        this.enderecoDTO = new EnderecoDTO(
                cliente.getCep(),
                cliente.getRua(),
                cliente.getBairro(),
                cliente.getComplemento(),
                cliente.getCidade(),
                cliente.getEstado()
        );

        this.urlCpfFoto = cliente.getUrlCpfFoto();
        this.dataCriacao = cliente.getDataCriacao();
        this.dataAtualizacao = cliente.getDataAtualizacao();
    }
}