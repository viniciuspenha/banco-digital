package br.com.viniciuspenha.bancodigital.repository;

import br.com.viniciuspenha.bancodigital.model.db.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}