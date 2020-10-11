package br.com.viniciuspenha.bancodigital.service;

import br.com.viniciuspenha.bancodigital.exception.NotFoundException;
import br.com.viniciuspenha.bancodigital.helper.AWSHelper;
import br.com.viniciuspenha.bancodigital.model.db.Conta;
import br.com.viniciuspenha.bancodigital.model.dto.ImagemDTO;
import br.com.viniciuspenha.bancodigital.model.dto.PessoaDTO;
import br.com.viniciuspenha.bancodigital.model.dto.EnderecoDTO;
import br.com.viniciuspenha.bancodigital.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final AWSHelper awsHelper;

    public ContaService(ContaRepository contaRepository, AWSHelper awsHelper) {
        this.contaRepository = contaRepository;
        this.awsHelper = awsHelper;
    }

    public PessoaDTO criaContaPasso1(PessoaDTO pessoaDTO) {
        Conta conta = contaRepository.save(new Conta(pessoaDTO));
        pessoaDTO.setId(conta.getId());
        return pessoaDTO;
    }

    public void criaContaPasso2(Integer id, EnderecoDTO enderecoDTO) throws NotFoundException {
        Conta conta = this.buscaContaPorId(id);
        conta.setEndereco(enderecoDTO);
        contaRepository.save(conta);
    }

    public void criaContaPasso3(Integer id, ImagemDTO imagemDTO) throws NotFoundException {
        Conta conta = this.buscaContaPorId(id);
        String url = sendToS3(conta.getId(), imagemDTO);
        conta.setUrlCpfFoto(url);
        conta.setDataAtualizacao(LocalDateTime.now());
    }

    private Conta buscaContaPorId(Integer id) throws NotFoundException {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        return contaOptional.orElseThrow(NotFoundException::new);
    }

    private String sendToS3(Integer idConta, ImagemDTO imagemDTO) {
        String fileName = idConta + "_" + LocalDateTime.now() + "." + imagemDTO.getMimeType();
        return awsHelper.sendImageToS3(imagemDTO.getImagemByteArray(), fileName);
    }
}