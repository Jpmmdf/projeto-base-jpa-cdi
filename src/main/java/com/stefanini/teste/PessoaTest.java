package com.stefanini.teste;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.stefanini.model.Pessoa;

public class PessoaTest {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("post");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Pessoa pessoa = new Pessoa("JOAO", "joaom.dev@hotmail.com1", LocalDate.of(1995, 8, 25), Boolean.TRUE);
		pessoa.setNome(null);
		em.persist(pessoa);
		em.getTransaction().commit();
//		em.flush();
		em.close();
		emf.close();
//		pessoa.nome = "VALOR";
//		System.out.println();
		System.out.println(pessoa);

	}

}
