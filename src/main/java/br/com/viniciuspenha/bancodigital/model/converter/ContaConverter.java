package br.com.viniciuspenha.bancodigital.model.converter;

import br.com.viniciuspenha.bancodigital.model.db.Conta;
import br.com.viniciuspenha.bancodigital.model.dto.ContaDTO;
import br.com.viniciuspenha.bancodigital.model.dto.EnderecoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContaConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContaConverter.class);

    public ContaDTO converterParaDTO(Conta conta) {
        try {
            return new ContaDTO(
                    conta.getNome(),
                    conta.getSobrenome(),
                    conta.getEmail(),
                    conta.getNascimento(),
                    conta.getCpf()
            );
        } catch (Exception e) {
            LOGGER.error("ContaConverter.converterParaDTO - Erro ao converter {}", e.getMessage());
            throw e;
        }
    }

    public Conta converterParaEntity(ContaDTO contaDTO) {
        try {
            return new Conta(
                    contaDTO.getNome(),
                    contaDTO.getSobrenome(),
                    contaDTO.getEmail(),
                    contaDTO.getNascimento(),
                    contaDTO.getCpf()
            );
        } catch (Exception e) {
            LOGGER.error("ContaConverter.converterParaEntity - Erro ao converter {}", e.getMessage());
            throw e;
        }
    }

    public void adicionarEnderecoNaEntity(Conta conta, EnderecoDTO enderecoDTO) {
        conta
    }
}