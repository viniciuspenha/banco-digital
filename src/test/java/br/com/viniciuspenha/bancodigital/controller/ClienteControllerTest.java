package br.com.viniciuspenha.bancodigital.controller;

import br.com.viniciuspenha.bancodigital.exception.ApiError;
import br.com.viniciuspenha.bancodigital.exception.ApiFieldError;
import br.com.viniciuspenha.bancodigital.model.dto.ClienteDTO;
import br.com.viniciuspenha.bancodigital.model.dto.DadosPessoaisDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void testCriacaoDeClienteComDadosPessoais() {
        ResponseEntity<ClienteDTO> forEntity = rest.postForEntity("/cliente", this.getDadosPessoaisDTOExemplo(), ClienteDTO.class);
        assertEquals(HttpStatus.CREATED, forEntity.getStatusCode());

        DadosPessoaisDTO dadosPessoaisDTO = forEntity.getBody().getDadosPessoaisDTO();
        assertEquals("Vinicius", dadosPessoaisDTO.getNome());
        assertEquals("Penha", dadosPessoaisDTO.getSobrenome());
        assertEquals("penhavinicius@hotmail.com", dadosPessoaisDTO.getEmail());
        assertEquals("1997-03-12", dadosPessoaisDTO.getDataNascimento().toString());
        assertEquals("42580985824", dadosPessoaisDTO.getCpf());
    }

    @Test
    public void testCriacaoDeClienteComDadosEmBranco() {
        DadosPessoaisDTO dadosPessoaisDTOExemplo = this.getDadosPessoaisEmBranco();
        ResponseEntity<ApiError> forEntity = rest.postForEntity("/cliente", dadosPessoaisDTOExemplo, ApiError.class);

        assertEquals(HttpStatus.BAD_REQUEST, forEntity.getStatusCode());

        List<ApiFieldError> fieldErrors = forEntity.getBody().getFieldErrors();
        final String mensagem = "não deve estar em branco";

        assertTrue(fieldErrors.contains(new ApiFieldError("nome", mensagem)));
        assertTrue(fieldErrors.contains(new ApiFieldError("sobrenome", mensagem)));
        assertTrue(fieldErrors.contains(new ApiFieldError("email", mensagem)));
        assertTrue(fieldErrors.contains(new ApiFieldError("cpf", mensagem)));
    }

    @Test
    public void testCriacaoDeClienteComDadosNulos() {
        DadosPessoaisDTO dadosPessoaisDTOExemplo = this.getDadosPessoaisNulos();
        ResponseEntity<ApiError> forEntity = rest.postForEntity("/cliente", dadosPessoaisDTOExemplo, ApiError.class);

        assertEquals(HttpStatus.BAD_REQUEST, forEntity.getStatusCode());

        List<ApiFieldError> fieldErrors = forEntity.getBody().getFieldErrors();
        final String mensagem = "não deve estar em branco";

        assertTrue(fieldErrors.contains(new ApiFieldError("nome", mensagem)));
        assertTrue(fieldErrors.contains(new ApiFieldError("sobrenome", mensagem)));
        assertTrue(fieldErrors.contains(new ApiFieldError("email", mensagem)));
        assertTrue(fieldErrors.contains(new ApiFieldError("cpf", mensagem)));
        assertTrue(fieldErrors.contains(new ApiFieldError("dataNascimento", "não deve ser nulo")));
    }

    @Test
    public void testCriacaoDeClienteComCpfInvalido() {
        DadosPessoaisDTO dadosPessoaisDTOExemplo = this.getDadosPessoaisDTOExemplo();
        dadosPessoaisDTOExemplo.setCpf("42580985823");
        ResponseEntity<ApiError> forEntity = rest.postForEntity("/cliente", dadosPessoaisDTOExemplo, ApiError.class);

        assertEquals(HttpStatus.BAD_REQUEST, forEntity.getStatusCode());

        List<ApiFieldError> fieldErrors = forEntity.getBody().getFieldErrors();
        final String mensagem = "número do registro de contribuinte individual brasileiro (CPF) inválido";

        assertTrue(fieldErrors.contains(new ApiFieldError("cpf", mensagem)));
    }

    @Test
    public void testCriacaoDeClienteComEmailInvalido() {
        DadosPessoaisDTO dadosPessoaisDTOExemplo = this.getDadosPessoaisDTOExemplo();
        dadosPessoaisDTOExemplo.setEmail("penha@");
        ResponseEntity<ApiError> forEntity = rest.postForEntity("/cliente", dadosPessoaisDTOExemplo, ApiError.class);

        assertEquals(HttpStatus.BAD_REQUEST, forEntity.getStatusCode());

        List<ApiFieldError> fieldErrors = forEntity.getBody().getFieldErrors();
        final String mensagem = "deve ser um endereço de e-mail bem formado";

        assertTrue(fieldErrors.contains(new ApiFieldError("email", mensagem)));
    }

    private DadosPessoaisDTO getDadosPessoaisDTOExemplo() {
        return new DadosPessoaisDTO(
                "Vinicius",
                "Penha",
                "penhavinicius@hotmail.com",
                LocalDate.parse("1997-03-12"),
                "42580985824"
        );
    }

    private DadosPessoaisDTO getDadosPessoaisEmBranco() {
        return new DadosPessoaisDTO(
                "",
                "",
                "",
                LocalDate.parse("1997-03-12"),
                ""
        );
    }

    private DadosPessoaisDTO getDadosPessoaisNulos() {
        return new DadosPessoaisDTO(
                null,
                null,
                null,
                null,
                null
        );
    }
}