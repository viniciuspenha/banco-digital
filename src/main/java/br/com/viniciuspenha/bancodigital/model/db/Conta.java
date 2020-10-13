package br.com.viniciuspenha.bancodigital.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Random;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "conta")
public class Conta {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @EmbeddedId
    private AgenciaContaId agenciaContaId;

    @Column(name = "codigo_banco")
    private String codigoBanco;

    @Column(name = "saldo")
    private BigDecimal saldo;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        Random random = new Random();
        this.agenciaContaId =
                new AgenciaContaId(
                    String.valueOf(random.nextInt(10000)),
                    String.valueOf(random.nextInt(100000000))
                );
        this.codigoBanco = "123";
        this.saldo = BigDecimal.ZERO;
    }
}