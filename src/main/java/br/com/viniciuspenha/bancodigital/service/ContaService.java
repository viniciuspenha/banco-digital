package br.com.viniciuspenha.bancodigital.service;

import br.com.viniciuspenha.bancodigital.exception.NotFoundException;
import br.com.viniciuspenha.bancodigital.exception.UnprocessableEntity;
import br.com.viniciuspenha.bancodigital.model.db.Cliente;
import br.com.viniciuspenha.bancodigital.model.db.Conta;
import br.com.viniciuspenha.bancodigital.model.dto.ContaDTO;
import br.com.viniciuspenha.bancodigital.repository.ContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContaService.class);

    private final ContaRepository contaRepository;
    private final ClienteService clienteService;
    private final PropostaService propostaService;

    public ContaService(ContaRepository contaRepository, ClienteService clienteService, PropostaService propostaService) {
        this.contaRepository = contaRepository;
        this.clienteService = clienteService;
        this.propostaService = propostaService;
    }

    public ContaDTO criaConta(Long clienteId) throws NotFoundException, UnprocessableEntity {
        LOGGER.info("ContaService.criaConta - Criando conta para o cliente {}", clienteId);
        Cliente cliente = clienteService.getClienteValidoComFotoDoCPF(clienteId);
        Conta conta = contaRepository.save(new Conta(cliente));
        LOGGER.info("ContaService.criaConta - Conta criada");
        LOGGER.info("ContaService.criaConta - Enviando email");
        // enviar email
        propostaService.atualizaPropostaAposCriacaoDeConta(clienteId, conta);
        return new ContaDTO(conta);
    }
}