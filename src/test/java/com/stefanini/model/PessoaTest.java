package com.stefanini.model;

import java.util.Set;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

public class PessoaTest {

    private Validator validator;
    private SessionFactory factoryJpa;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
                .build();
        factoryJpa = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    /**
     * Buscando uma Pessoa do Modo Antido
     *
     * @param session
     * @param name
     * @return
     */
    private Pessoa findBuscarPessoaAntigo(Session session, String name) {
        Query<Pessoa> query = session.createQuery("select p from Pessoa p where p.nome=:nome");
        query.setParameter("nome", name);
        Pessoa person = query.uniqueResult();
        return person;
    }

    /**
     * Buscando uma Pessoa do Modo novo
     * Mais seguro pois passa a classe retornada como parametro.
     *
     * @param session
     * @param name
     * @return
     */
    private Pessoa findBuscarPessoaNovo(Session session, String name) {
        TypedQuery<Pessoa> q2 =
                session.createQuery(" select p from Pessoa p where p.nome=:nome", Pessoa.class);
        q2.setParameter("nome", name);
        Pessoa person = q2.getSingleResult();
        return person;
    }

    /**
     * EFETUAR A BUSCAR COM QUERY
     * QUANDO NÃO POSSUI VALOR RETORNO NULL
     */
    @Test
    public void buscarPessoaComQuery() {
        try (Session session = factoryJpa.openSession()) {
            Pessoa pessoa1 = findBuscarPessoaAntigo(session, "JOAO1");
            System.out.println("Antigo: " + pessoa1);
        }
    }

    /**
     * EFETUAR A BUSCAR COM TYPEDQUERY
     * QUANDO NÃO POSSUI LANCA UMA NoResultException
     */
    @Test(expected = Exception.class)
    public void buscarPessoaComTypedQuery() {
        try (Session session = factoryJpa.openSession()) {
            Pessoa pessoa1 = findBuscarPessoaNovo(session, "JOAO1");
            System.out.println("Novo: " + pessoa1);
        }
    }

    //    @Test
    public void naoDeveAceitarSobrenomeCurto() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Ana");
//	    cliente.setSobrenome("S.");

        Set<ConstraintViolation<Pessoa>> restricoes = validator.validate(pessoa);
        System.out.println(restricoes.size());
//	    assertThat(restricoes, hasSize(1));
//	    assertThat(getNomePrimeiraPropriedade(restricoes), is("sobrenome"));
    }

}
