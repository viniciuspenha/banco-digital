package br.com.viniciuspenha.bancodigital.service;

import br.com.viniciuspenha.bancodigital.model.converter.ContaConverter;
import br.com.viniciuspenha.bancodigital.model.db.Conta;
import br.com.viniciuspenha.bancodigital.model.dto.ContaDTO;
import br.com.viniciuspenha.bancodigital.repository.ContaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ContaConverter contaConverter;

    public ContaService(ContaRepository contaRepository, ContaConverter contaConverter) {
        this.contaRepository = contaRepository;
        this.contaConverter = contaConverter;
    }

    public void passo1(ContaDTO contaDTO) {
        Conta conta = contaConverter.converterParaEntity(contaDTO);
        contaRepository.save(conta);
    }
}