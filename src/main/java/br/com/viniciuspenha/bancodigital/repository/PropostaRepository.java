package br.com.viniciuspenha.bancodigital.repository;

import br.com.viniciuspenha.bancodigital.model.db.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByClienteId(Long clienteId);
}