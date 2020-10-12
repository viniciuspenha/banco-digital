package br.com.viniciuspenha.bancodigital.controller;

import br.com.viniciuspenha.bancodigital.exception.NotFoundException;
import br.com.viniciuspenha.bancodigital.exception.UnprocessableEntity;
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
    public void criaClienteComDadosPessoais(@RequestBody @Valid DadosPessoaisDTO dadosPessoaisDTO) {
        clienteService.criaClienteComDadosPessoais(dadosPessoaisDTO);
    }

    @PostMapping("/{id}/endereco")
    @ResponseStatus(HttpStatus.CREATED)
    public void incluiEnderecoDoCliente(@PathVariable Integer id, @RequestBody @Valid EnderecoDTO enderecoDTO) throws NotFoundException {
        clienteService.incluiEnderecoDoCliente(id, enderecoDTO);
    }

    @PostMapping("/{id}/cpf")
    @ResponseStatus(HttpStatus.CREATED)
    public void incluiCpfFoto(@PathVariable Integer id, @RequestBody ImagemDTO imagemDTO) throws NotFoundException, UnprocessableEntity {
        clienteService.incluiCpfFoto(id, imagemDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO getCliente(@PathVariable Integer id) throws NotFoundException, UnprocessableEntity {
        return clienteService.getClienteById(id);
    }
}