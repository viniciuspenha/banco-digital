package br.com.viniciuspenha.bancodigital.model.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "proposta")
public class Proposta {

    @Column(name = "cliente_id")
    private Long clienteId;

    @EmbeddedId
    private AgenciaContaId agenciaContaId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusProposta status;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}