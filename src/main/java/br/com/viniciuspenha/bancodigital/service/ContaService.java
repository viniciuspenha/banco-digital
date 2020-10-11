package br.com.viniciuspenha.bancodigital.service;

import br.com.viniciuspenha.bancodigital.exception.NotFoundException;
import br.com.viniciuspenha.bancodigital.model.converter.ContaConverter;
import br.com.viniciuspenha.bancodigital.model.db.Conta;
import br.com.viniciuspenha.bancodigital.model.dto.PessoaDTO;
import br.com.viniciuspenha.bancodigital.model.dto.EnderecoDTO;
import br.com.viniciuspenha.bancodigital.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ContaConverter contaConverter;

    public ContaService(ContaRepository contaRepository, ContaConverter contaConverter) {
        this.contaRepository = contaRepository;
        this.contaConverter = contaConverter;
    }

    public PessoaDTO criaContaPasso1(PessoaDTO pessoaDTO) {
        Conta conta = contaRepository.save(new Conta(pessoaDTO));
        pessoaDTO.setId(conta.getId());
        return pessoaDTO;
    }

    public void criaContaPasso2(Integer id, EnderecoDTO enderecoDTO) throws NotFoundException {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        Conta conta = contaOptional.orElseThrow(NotFoundException::new);
        conta.setEndereco(enderecoDTO);
        contaRepository.save(conta);
    }
}