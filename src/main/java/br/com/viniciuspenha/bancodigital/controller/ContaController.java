package br.com.viniciuspenha.bancodigital.controller;

import br.com.viniciuspenha.bancodigital.model.dto.ContaDTO;
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

    @PostMapping()
    public void passo1(@RequestBody @Valid ContaDTO contaDTO) {
        contaService.passo1(contaDTO);
    }
}