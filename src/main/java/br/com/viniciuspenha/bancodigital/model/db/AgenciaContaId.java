package br.com.viniciuspenha.bancodigital.model.db;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class AgenciaContaId implements Serializable {

    @Column(name = "agencia")
    private String agencia;

    @Column(name = "conta")
    private String conta;

    public AgenciaContaId(String agencia, String conta) {
        this.agencia = agencia;
        this.conta = conta;
    }
}