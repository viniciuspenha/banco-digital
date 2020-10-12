package br.com.viniciuspenha.bancodigital.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "proposta")
public class Proposta {

    @Id
    @Column(name = "cliente_id")
    private Long clienteId;

    @Embedded
    private AgenciaContaId agenciaContaId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusProposta status;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public Proposta(Long clienteId, boolean aceite) {
        this.clienteId = clienteId;
        this.setAceite(aceite);
        this.dataCriacao = LocalDateTime.now();
    }

    public void setAceite(boolean aceite) {
        if (aceite) {
            this.status = StatusProposta.PENDENTE;
        } else {
            this.status = StatusProposta.RECUSADA;
        }
    }
}