package br.com.viniciuspenha.bancodigital.controller;

import br.com.viniciuspenha.bancodigital.exception.NotFoundException;
import br.com.viniciuspenha.bancodigital.exception.UnprocessableEntity;
import br.com.viniciuspenha.bancodigital.model.dto.ClienteDTO;
import br.com.viniciuspenha.bancodigital.model.dto.EnderecoDTO;
import br.com.viniciuspenha.bancodigital.model.dto.ImagemDTO;
import br.com.viniciuspenha.bancodigital.model.dto.DadosPessoaisDTO;
import br.com.viniciuspenha.bancodigital.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/passo1")
    @ResponseStatus(HttpStatus.CREATED)
    public void criaContaPasso1(@RequestBody @Valid DadosPessoaisDTO dadosPessoaisDTO) {
        contaService.criaContaPasso1(dadosPessoaisDTO);
    }

    @PostMapping("/{id}/passo2")
    @ResponseStatus(HttpStatus.CREATED)
    public void criaContaPasso2(@PathVariable Integer id, @RequestBody @Valid EnderecoDTO enderecoDTO) throws NotFoundException {
        contaService.criaContaPasso2(id, enderecoDTO);
    }

    @PostMapping("/{id}/passo3")
    @ResponseStatus(HttpStatus.CREATED)
    public void criaContaPasso3(@PathVariable Integer id, @RequestBody ImagemDTO imagemDTO) throws NotFoundException, UnprocessableEntity {
        contaService.criaContaPasso3(id, imagemDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO getConta(@PathVariable Integer id) throws NotFoundException, UnprocessableEntity {
        return contaService.getContaById(id);
    }
}