package br.com.viniciuspenha.bancodigital.model.converter;

import br.com.viniciuspenha.bancodigital.model.db.Conta;
import br.com.viniciuspenha.bancodigital.model.dto.PessoaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContaConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContaConverter.class);

    public PessoaDTO converterParaDTO(Conta conta) {
        try {
            return new PessoaDTO(
                    conta.getNome(),
                    conta.getSobrenome(),
                    conta.getEmail(),
                    conta.getDataNascimento(),
                    conta.getCpf()
            );
        } catch (Exception e) {
            LOGGER.error("ContaConverter.converterParaDTO - Erro ao converter {}", e.getMessage());
            throw e;
        }
    }
}