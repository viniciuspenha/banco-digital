package br.com.viniciuspenha.bancodigital.model.db;

import br.com.viniciuspenha.bancodigital.model.dto.EnderecoDTO;
import br.com.viniciuspenha.bancodigital.model.dto.PessoaDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public Conta(PessoaDTO pessoaDTO) {
        this.nome = pessoaDTO.getNome();
        this.sobrenome = pessoaDTO.getSobrenome();
        this.email = pessoaDTO.getEmail();
        this.dataNascimento = pessoaDTO.getDataNascimento();
        this.cpf = pessoaDTO.getCpf();
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
}