package br.com.viniciuspenha.bancodigital.model.dto;

import br.com.viniciuspenha.bancodigital.model.db.Conta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContaDTO {

    @JsonProperty("clienteId")
    private Long clienteId;

    @JsonProperty("agencia")
    private String agencia;

    @JsonProperty("conta")
    private String conta;

    @JsonProperty("codigoBanco")
    private String codigoBanco;

    @JsonProperty("saldo")
    private BigDecimal saldo;

    public ContaDTO(Conta conta) {
        this.clienteId = conta.getCliente().getId();
        this.agencia = conta.getAgenciaContaId().getAgencia();
        this.conta = conta.getAgenciaContaId().getConta();
        this.codigoBanco = conta.getCodigoBanco();
        this.saldo = conta.getSaldo();
    }
}