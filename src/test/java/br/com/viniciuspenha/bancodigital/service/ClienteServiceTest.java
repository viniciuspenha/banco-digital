package br.com.viniciuspenha.bancodigital.service;

import br.com.viniciuspenha.bancodigital.exception.ValidationException;
import br.com.viniciuspenha.bancodigital.model.db.Cliente;
import br.com.viniciuspenha.bancodigital.model.dto.ClienteDTO;
import br.com.viniciuspenha.bancodigital.model.dto.DadosPessoaisDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteServiceTest {

    @Mock
    private ClienteService clienteServiceMock;

    @Test
    public void testCriacaoDeCliente() throws ValidationException {
        DadosPessoaisDTO dadosPessoaisDTOExemplo = this.getDadosPessoaisDTOExemplo();
        ClienteDTO clienteDTOExemplo = this.getClienteDTO();

        when(clienteServiceMock.criaClienteComDadosPessoais(dadosPessoaisDTOExemplo)).thenReturn(clienteDTOExemplo);

        ClienteDTO clienteDTO = clienteServiceMock.criaClienteComDadosPessoais(dadosPessoaisDTOExemplo);

        assertEquals("Vinicius", clienteDTO.getDadosPessoaisDTO().getNome());
        assertEquals("Penha", clienteDTO.getDadosPessoaisDTO().getSobrenome());
        assertEquals("penhavinicius@hotmail.com", clienteDTO.getDadosPessoaisDTO().getEmail());
        assertEquals("1997-03-12", clienteDTO.getDadosPessoaisDTO().getDataNascimento().toString());
        assertEquals("42580985824", clienteDTO.getDadosPessoaisDTO().getCpf());
    }

    private ClienteDTO getClienteDTO() {
        Cliente cliente = new Cliente(this.getDadosPessoaisDTOExemplo());
        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        clienteDTO.setId(1L);
        return clienteDTO;
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
}