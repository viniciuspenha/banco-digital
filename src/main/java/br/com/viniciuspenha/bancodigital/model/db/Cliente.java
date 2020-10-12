package br.com.viniciuspenha.bancodigital.model.db;

import br.com.viniciuspenha.bancodigital.exception.UnprocessableEntity;
import br.com.viniciuspenha.bancodigital.model.dto.EnderecoDTO;
import br.com.viniciuspenha.bancodigital.model.dto.DadosPessoaisDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "email")
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "cep")
    private String cep;

    @Column(name = "rua")
    private String rua;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "url_cpf_foto")
    private String urlCpfFoto;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(DadosPessoaisDTO dadosPessoaisDTO) {
        this.nome = dadosPessoaisDTO.getNome();
        this.sobrenome = dadosPessoaisDTO.getSobrenome();
        this.email = dadosPessoaisDTO.getEmail();
        this.dataNascimento = dadosPessoaisDTO.getDataNascimento();
        this.cpf = dadosPessoaisDTO.getCpf();
        this.dataCriacao = LocalDateTime.now();
    }

    public void setEndereco(EnderecoDTO enderecoDTO) {
        this.setCep(enderecoDTO.getCep());
        this.setRua(enderecoDTO.getRua());
        this.setBairro(enderecoDTO.getBairro());
        this.setComplemento(enderecoDTO.getComplemento());
        this.setCidade(enderecoDTO.getCidade());
        this.setEstado(enderecoDTO.getEstado());
        this.setDataAtualizacao(LocalDateTime.now());
    }

    public boolean estaValida() throws UnprocessableEntity {
        if (this.getId() != null
                && this.getNome() != null
                && this.getSobrenome() != null
                && this.getEmail() != null
                && this.getDataNascimento() != null
                && this.getCpf() != null
                && this.getCep() != null
                && this.getRua() != null
                && this.getBairro() != null
                && this.getComplemento() != null
                && this.getCidade() != null
                && this.getEstado() != null) {
            return true;
        } else {
            throw new UnprocessableEntity("Cliente inválido. Preencha todos os campos obrigatórios");
        }
    }

    public boolean estaValidaComFotoDoCPF() throws UnprocessableEntity {
        if (this.estaValida() && this.getUrlCpfFoto() != null) {
            return true;
        } else {
            throw new UnprocessableEntity("Cliente inválida. Preencha todos os campos obrigatórios");
        }
    }
}