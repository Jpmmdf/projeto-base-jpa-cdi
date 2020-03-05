package com.stefanini.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {



    /**
     * Serializacao da Classe
     */
    private static final long serialVersionUID = 1L;
    /**
     * ID da Tabela
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CO_SEQ_ENDERECO")
    private Long id;

    @Column(name = "DS_CEP")
    private String cep;

    @Column(name = "CO_UF")
    private String uf;

    @Column(name = "DS_CIDADE")
    private String localidade;

    @Column(name = "DS_BAIRRO")
    private String bairro;

    @Column(name = "DS_COMPLEMENTO")
    private String complemento;

    @Column(name = "DS_LOGRADOURO")
    private String logradouro;

    @ManyToOne
    @JoinColumn(name = "co_seq_pessoa", referencedColumnName = "co_seq_pessoa", nullable = false)
    private Pessoa pessoa;



}
