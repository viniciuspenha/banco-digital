package br.com.viniciuspenha.bancodigital.model.db;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "conta")
public class Conta {

    private Integer agencia;
    private Integer conta;
    private Integer codigoBanco = 123;
    private BigDecimal saldo;

}