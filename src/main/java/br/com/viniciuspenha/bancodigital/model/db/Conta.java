package br.com.viniciuspenha.bancodigital.model.db;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Random;

@Getter
@Entity
@Table(name = "conta")
public class Conta {

    private Long clienteId;
    private String agencia;
    private String conta;
    private String codigoBanco;
    private BigDecimal saldo;

    public Conta(Long clienteId) {
        this.clienteId = clienteId;
        Random random = new Random();
        this.agencia = String.valueOf(random.nextInt(10000));;
        this.conta = String.valueOf(random.nextInt(100000000));
        this.codigoBanco = "123";
        this.saldo = BigDecimal.ZERO;
    }
}