package br.com.viniciuspenha.bancodigital.controller;

import br.com.viniciuspenha.bancodigital.exception.*;
import br.com.viniciuspenha.bancodigital.model.db.Cliente;
import br.com.viniciuspenha.bancodigital.model.dto.ClienteDTO;
import br.com.viniciuspenha.bancodigital.model.dto.EnderecoDTO;
import br.com.viniciuspenha.bancodigital.model.dto.ImagemDTO;
import br.com.viniciuspenha.bancodigital.model.dto.DadosPessoaisDTO;
import br.com.viniciuspenha.bancodigital.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO criaClienteComDadosPessoais(@RequestBody @Valid DadosPessoaisDTO dadosPessoaisDTO) throws ValidationException {
        return clienteService.criaClienteComDadosPessoais(dadosPessoaisDTO);
    }

    @PostMapping("/{id}/endereco")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO incluiEnderecoDoCliente(@PathVariable Long id, @RequestBody @Valid EnderecoDTO enderecoDTO) throws NotFoundException {
        return clienteService.incluiEnderecoDoCliente(id, enderecoDTO);
    }

    @PostMapping("/{id}/cpf")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO incluiCpfFoto(@PathVariable Long id, @RequestBody @Valid ImagemDTO imagemDTO) throws NotFoundException, UnprocessableEntity {
        return clienteService.incluiCpfFoto(id, imagemDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO getCliente(@PathVariable Long id) throws NotFoundException, UnprocessableEntity {
        Cliente cliente = clienteService.getClienteValidoComFotoDoCPF(id);
        return new ClienteDTO(cliente);
    }
}