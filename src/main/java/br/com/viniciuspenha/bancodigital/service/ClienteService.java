package br.com.viniciuspenha.bancodigital.service;

import br.com.viniciuspenha.bancodigital.exception.EmailDuplicadoException;
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
import java.time.format.DateTimeFormatter;
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

    public ClienteDTO criaClienteComDadosPessoais(DadosPessoaisDTO dadosPessoaisDTO) throws EmailDuplicadoException {
        this.validarEmail(dadosPessoaisDTO.getEmail());
        LOGGER.info("ClienteService.criaClienteComDadosPessoais - Criando cliente - email {}", dadosPessoaisDTO.getEmail());
        Cliente cliente = clienteRepository.save(new Cliente(dadosPessoaisDTO));
        LOGGER.info("ClienteService.criaClienteComDadosPessoais - Cliente criado - id {}", cliente.getId());
        return new ClienteDTO(cliente);
    }

    private void validarEmail(String email) throws EmailDuplicadoException {
        LOGGER.info("ClienteService.validarEmail - Validando email {}", email);
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if (cliente.isPresent()) {
            throw new EmailDuplicadoException();
        }
        LOGGER.info("ClienteService.validarEmail - Email valido");
    }

    public ClienteDTO incluiEnderecoDoCliente(Long id, EnderecoDTO enderecoDTO) throws NotFoundException {
        LOGGER.info("ClienteService.incluiEnderecoDoCliente - Cliente id {}", id);
        Cliente cliente = this.getClienteById(id);
        cliente.setEndereco(enderecoDTO);
        clienteRepository.save(cliente);
        LOGGER.info("ClienteService.incluiEnderecoDoCliente - Cliente atualizado");
        return new ClienteDTO(cliente);
    }

    public ClienteDTO incluiCpfFoto(Long id, ImagemDTO imagemDTO) throws NotFoundException, UnprocessableEntity {
        LOGGER.info("ClienteService.incluiCpfFoto - Cliente id {}", id);
        Cliente cliente = this.getClienteById(id);
        LOGGER.info("ClienteService.incluiCpfFoto - Validando conta...");
        cliente.estaValida();
        LOGGER.info("ClienteService.incluiCpfFoto - Conta valida");
        String url = sendToS3(cliente.getId(), imagemDTO);
        cliente.setUrlCpfFoto(url);
        cliente.setDataAtualizacao(LocalDateTime.now());
        clienteRepository.save(cliente);
        LOGGER.info("ClienteService.incluiCpfFoto - Cliente atualizado");
        return new ClienteDTO(cliente);
    }

    public Cliente getClienteValidoComFotoDoCPF(Long id) throws NotFoundException, UnprocessableEntity {
        LOGGER.info("ClienteService.getClienteValidoComFotoDoCPF - Cliente id {}", id);
        Cliente cliente = getClienteById(id);
        LOGGER.info("ClienteService.getClienteValidoComFotoDoCPF - Validando com foto do cpf");
        cliente.estaValidaComFotoDoCPF();
        LOGGER.info("ClienteService.getClienteValidoComFotoDoCPF - Valido");
        return cliente;
    }

    private Cliente getClienteById(Long id) throws NotFoundException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(NotFoundException::new);
    }

    private String sendToS3(Long idConta, ImagemDTO imagemDTO) {
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String fileName = data + "_" + idConta + "." + imagemDTO.getExtensao();
        return awsHelper.sendImageToS3(imagemDTO.getImagemByteArray(), fileName);
    }
}