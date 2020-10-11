package br.com.viniciuspenha.bancodigital.controller;

import br.com.viniciuspenha.bancodigital.exception.NotFoundException;
import br.com.viniciuspenha.bancodigital.model.dto.EnderecoDTO;
import br.com.viniciuspenha.bancodigital.model.dto.ImagemDTO;
import br.com.viniciuspenha.bancodigital.model.dto.PessoaDTO;
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
    public PessoaDTO criaContaPasso1(@RequestBody @Valid PessoaDTO pessoaDTO) {
        return contaService.criaContaPasso1(pessoaDTO);
    }

    @PostMapping("/{id}/passo2")
    @ResponseStatus(HttpStatus.CREATED)
    public void criaContaPasso2(@PathVariable Integer id, @RequestBody @Valid EnderecoDTO enderecoDTO) throws NotFoundException {
        contaService.criaContaPasso2(id, enderecoDTO);
    }

    @PostMapping("/{id}/passo3")
    @ResponseStatus(HttpStatus.CREATED)
    public void criaContaPasso3(@PathVariable Integer id, @RequestBody ImagemDTO imagemDTO) throws NotFoundException {
        contaService.criaContaPasso3(id, imagemDTO);
    }
}