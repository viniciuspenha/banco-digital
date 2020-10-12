package br.com.viniciuspenha.bancodigital.service;

import br.com.viniciuspenha.bancodigital.exception.NotFoundException;
import br.com.viniciuspenha.bancodigital.exception.UnprocessableEntity;
import br.com.viniciuspenha.bancodigital.helper.AWSHelper;
import br.com.viniciuspenha.bancodigital.model.db.Cliente;
import br.com.viniciuspenha.bancodigital.model.dto.ClienteDTO;
import br.com.viniciuspenha.bancodigital.model.dto.ImagemDTO;
import br.com.viniciuspenha.bancodigital.model.dto.DadosPessoaisDTO;
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

    public void criaContaPasso1(DadosPessoaisDTO dadosPessoaisDTO) {
        contaRepository.save(new Cliente(dadosPessoaisDTO));
    }

    public void criaContaPasso2(Integer id, EnderecoDTO enderecoDTO) throws NotFoundException {
        Cliente cliente = this.buscaContaPorId(id);
        cliente.setEndereco(enderecoDTO);
        contaRepository.save(cliente);
    }

    public void criaContaPasso3(Integer id, ImagemDTO imagemDTO) throws NotFoundException, UnprocessableEntity {
        Cliente cliente = this.buscaContaPorId(id);
        cliente.estaValida();
        String url = sendToS3(cliente.getId(), imagemDTO);
        cliente.setUrlCpfFoto(url);
        cliente.setDataAtualizacao(LocalDateTime.now());
    }

    private Cliente buscaContaPorId(Integer id) throws NotFoundException {
        Optional<Cliente> contaOptional = contaRepository.findById(id);
        return contaOptional.orElseThrow(NotFoundException::new);
    }

    private String sendToS3(Integer idConta, ImagemDTO imagemDTO) {
        String fileName = idConta + "_" + LocalDateTime.now() + "." + imagemDTO.getMimeType();
        return awsHelper.sendImageToS3(imagemDTO.getImagemByteArray(), fileName);
    }

    public ClienteDTO getContaById(Integer id) throws NotFoundException, UnprocessableEntity {
        Cliente cliente = buscaContaPorId(id);
        cliente.estaValidaComFotoDoCPF();
        return new ClienteDTO(cliente);
    }
}