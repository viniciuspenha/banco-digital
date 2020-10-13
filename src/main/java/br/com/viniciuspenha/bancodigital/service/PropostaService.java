package br.com.viniciuspenha.bancodigital.service;

import br.com.viniciuspenha.bancodigital.exception.NotFoundException;
import br.com.viniciuspenha.bancodigital.exception.UnprocessableEntity;
import br.com.viniciuspenha.bancodigital.model.db.*;
import br.com.viniciuspenha.bancodigital.model.dto.ClienteDTO;
import br.com.viniciuspenha.bancodigital.rabbit.ValidacaoQueueSender;
import br.com.viniciuspenha.bancodigital.repository.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PropostaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropostaService.class);

    private final PropostaRepository propostaRepository;
    private final ClienteService clienteService;
    private final ValidacaoQueueSender validacaoQueueSender;

    public PropostaService(PropostaRepository propostaRepository, ClienteService clienteService, ValidacaoQueueSender validacaoQueueSender) {
        this.propostaRepository = propostaRepository;
        this.clienteService = clienteService;
        this.validacaoQueueSender = validacaoQueueSender;
    }

    public void criaProposta(Long clienteId, boolean aceite) throws NotFoundException, UnprocessableEntity {
        LOGGER.info("PropostaService.criaProposta - Verificando...");
        Cliente cliente = clienteService.getClienteValidoComFotoDoCPF(clienteId);
        Proposta proposta = this.validaSeClienteJaTemProposta(clienteId);
        if (proposta != null) {
            this.atualizaAceiteDaProposta(proposta, aceite);
            if (aceite) {
                validacaoQueueSender.send(new ClienteDTO(cliente));
            }
            return;
        }
        LOGGER.info("PropostaService.criaProposta - Criando proposta...");
        propostaRepository.save(new Proposta(cliente.getId(), aceite));
        LOGGER.info("PropostaService.criaProposta - Proposta criada");
        validacaoQueueSender.send(new ClienteDTO(cliente));
    }

    public void atualizaPropostaAposCriacaoDeConta(Long clienteId, Conta conta) throws NotFoundException {
        LOGGER.info("PropostaService.atualizaPropostaAposCriacaoDeConta - Atualizando proposta...");
        Optional<Proposta> propostaByClienteId = propostaRepository.findByClienteId(clienteId);
        Proposta proposta = propostaByClienteId.orElseThrow(NotFoundException::new);
        proposta.setAgenciaContaId(conta.getAgenciaContaId());
        proposta.setStatus(StatusPropostaEnum.ACEITA);
        proposta.setDataAtualizacao(LocalDateTime.now());
        propostaRepository.save(proposta);
        LOGGER.info("PropostaService.atualizaPropostaAposCriacaoDeConta - Atualizada");
    }

    private void atualizaAceiteDaProposta(Proposta proposta, boolean aceite) {
        LOGGER.info("PropostaService.atualizaAceiteDaProposta - Atualizando proposta...");
        proposta.setAceite(aceite);
        proposta.setDataAtualizacao(LocalDateTime.now());
        propostaRepository.save(proposta);
        LOGGER.info("PropostaService.atualizaAceiteDaProposta - Proposta atualizada");
    }

    private Proposta validaSeClienteJaTemProposta(Long clienteId) {
        Optional<Proposta> proposta = propostaRepository.findByClienteId(clienteId);
        return proposta.orElse(null);
    }
}