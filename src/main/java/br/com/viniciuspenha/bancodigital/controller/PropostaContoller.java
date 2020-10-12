package br.com.viniciuspenha.bancodigital.controller;

import br.com.viniciuspenha.bancodigital.exception.NotFoundException;
import br.com.viniciuspenha.bancodigital.exception.UnprocessableEntity;
import br.com.viniciuspenha.bancodigital.service.PropostaService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposta")
public class PropostaContoller {

    private final PropostaService propostaService;

    public PropostaContoller(PropostaService propostaService) {
        this.propostaService = propostaService;
    }

    @PostMapping("/{clienteId}/{aceite}")
    public void criaProposta(@PathVariable Long clienteId, @PathVariable boolean aceite) throws NotFoundException, UnprocessableEntity {
        propostaService.criaProposta(clienteId, aceite);
    }
}