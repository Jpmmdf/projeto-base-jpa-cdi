package com.stefanini.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "co_seq_pessoa")
	private Long id;
//	@NotNull(message = "Nome da Pessoa nao pode ser nulo")
	@Column(name = "no_nome")
	private String nome;
	@Email
	@NotNull
	@Column(name = "ds_email")
	private String email;
	@Column(name = "dt_nascimento")
	private LocalDate dataNascimento;
	@Column(name = "st_pessoa")
	private Boolean situacao;

	public Pessoa() {
//		System.out.println("CHAMEI");
	}

	public Pessoa(String nome, String email, LocalDate dataNascimento, Boolean situacao) {
		super();
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.situacao = situacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Pessoa [getNome()=" + getNome() + ", getEmail()=" + getEmail() + ", getDataNascimento()="
				+ getDataNascimento() + ", getSituacao()=" + getSituacao() + "]";
	}

}
