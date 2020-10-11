package br.com.viniciuspenha.bancodigital.controller;

import br.com.viniciuspenha.bancodigital.model.dto.ContaDTO;
import br.com.viniciuspenha.bancodigital.model.dto.EnderecoDTO;
import br.com.viniciuspenha.bancodigital.service.ContaService;
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
    public void criaContaPasso1(@RequestBody @Valid ContaDTO contaDTO) {
        contaService.criaContaPasso1(contaDTO);
    }

    @PostMapping("/{id}/passo2")
    public void criaContaPasso2(@PathVariable Integer id, @RequestBody @Valid EnderecoDTO enderecoDTO) {
        contaService.criaContaPasso2(id, enderecoDTO);
    }
}