package br.com.viniciuspenha.bancodigital.service;

import br.com.viniciuspenha.bancodigital.exception.NotFoundException;
import br.com.viniciuspenha.bancodigital.exception.UnprocessableEntity;
import br.com.viniciuspenha.bancodigital.helper.AWSHelper;
import br.com.viniciuspenha.bancodigital.model.db.Cliente;
import br.com.viniciuspenha.bancodigital.model.dto.ClienteDTO;
import br.com.viniciuspenha.bancodigital.model.dto.ImagemDTO;
import br.com.viniciuspenha.bancodigital.model.dto.DadosPessoaisDTO;
import br.com.viniciuspenha.bancodigital.model.dto.EnderecoDTO;
import br.com.viniciuspenha.bancodigital.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ClienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteRepository clienteRepository;
    private final AWSHelper awsHelper;

    public ClienteService(ClienteRepository clienteRepository, AWSHelper awsHelper) {
        this.clienteRepository = clienteRepository;
        this.awsHelper = awsHelper;
    }

    public void criaClienteComDadosPessoais(DadosPessoaisDTO dadosPessoaisDTO) {
        LOGGER.info("ClienteService.criaClienteComDadosPessoais - Criando cliente (passo 1) - email {}", dadosPessoaisDTO.getEmail());
        clienteRepository.save(new Cliente(dadosPessoaisDTO));
    }

    public void incluiEnderecoDoCliente(Long id, EnderecoDTO enderecoDTO) throws NotFoundException {
        Cliente cliente = this.buscaContaPorId(id);
        cliente.setEndereco(enderecoDTO);
        clienteRepository.save(cliente);
    }

    public void incluiCpfFoto(Long id, ImagemDTO imagemDTO) throws NotFoundException, UnprocessableEntity {
        Cliente cliente = this.buscaContaPorId(id);
        cliente.estaValida();
        String url = sendToS3(cliente.getId(), imagemDTO);
        cliente.setUrlCpfFoto(url);
        cliente.setDataAtualizacao(LocalDateTime.now());
    }

    private Cliente buscaContaPorId(Long id) throws NotFoundException {
        Optional<Cliente> contaOptional = clienteRepository.findById(id);
        return contaOptional.orElseThrow(NotFoundException::new);
    }

    private String sendToS3(Long idConta, ImagemDTO imagemDTO) {
        String fileName = idConta + "_" + LocalDateTime.now() + "." + imagemDTO.getMimeType();
        return awsHelper.sendImageToS3(imagemDTO.getImagemByteArray(), fileName);
    }

    public ClienteDTO getClienteById(Long id) throws NotFoundException, UnprocessableEntity {
        Cliente cliente = buscaContaPorId(id);
        cliente.estaValidaComFotoDoCPF();
        return new ClienteDTO(cliente);
    }
}