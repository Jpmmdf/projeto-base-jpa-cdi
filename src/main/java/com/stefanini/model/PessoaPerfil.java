package com.stefanini.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_pessoa_perfil")
public class PessoaPerfil implements Serializable {


    @Id
    @Column(name = "co_seq_pessoal_perfil")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "co_seq_perfil", referencedColumnName = "co_seq_perfil", nullable = false)
    private Perfil perfil;
    @ManyToOne
    @JoinColumn(name = "co_seq_pessoa", referencedColumnName = "co_seq_pessoa", nullable = false)
    private Pessoa pessoa;

    public PessoaPerfil() {
		// TODO Auto-generated constructor stub
	}

    public PessoaPerfil(Perfil perfil, Pessoa pessoa) {
        this.perfil = perfil;
        this.pessoa = pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
