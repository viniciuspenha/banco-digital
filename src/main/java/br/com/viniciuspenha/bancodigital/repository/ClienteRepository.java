package br.com.viniciuspenha.bancodigital.repository;

import br.com.viniciuspenha.bancodigital.model.db.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);
}