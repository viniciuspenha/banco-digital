package br.com.viniciuspenha.bancodigital.service;

import br.com.viniciuspenha.bancodigital.exception.NotFoundException;
import br.com.viniciuspenha.bancodigital.exception.UnprocessableEntity;
import br.com.viniciuspenha.bancodigital.model.db.Cliente;
import br.com.viniciuspenha.bancodigital.model.db.Conta;
import br.com.viniciuspenha.bancodigital.repository.ContaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteService clienteService;

    public ContaService(ContaRepository contaRepository, ClienteService clienteService) {
        this.contaRepository = contaRepository;
        this.clienteService = clienteService;
    }

    public void criaConta(Long clienteId) throws NotFoundException, UnprocessableEntity {
        Cliente cliente = clienteService.getClienteValidoComFotoDoCPF(clienteId);
        contaRepository.save(new Conta(cliente));
        // enviar email
    }
}