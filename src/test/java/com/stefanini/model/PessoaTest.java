package com.stefanini.model;

import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

public class PessoaTest {

    private Validator validator;
    private SessionFactory factoryJpa;
    private Boolean h2Carregador = Boolean.FALSE;

    private  String nome = "JOAO";

    @Before
    public void setUp() {
        runScrip();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
                .build();
        factoryJpa = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public void runScrip() {
        Connection conn = null;
        try {
            conn = DriverManager.
                    getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
            if (conn != null) {
                final Statement st = conn.createStatement();
                final ResultSet rs = st.executeQuery("show tables");
                while (rs.next()) {
                    h2Carregador = true;
                }
                if (!h2Carregador) {
                    ClassLoader classLoader = getClass().getClassLoader();
                    File file = new File(classLoader.getResource("db.sql").getFile());
                    System.out.println("Carregado o SCRIPT");
                    RunScript.execute(conn, new FileReader(file));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * Buscando uma Pessoa do Modo novo
     * Mais seguro pois passa a classe retornada como parametro.
     *
     * @param session
     * @param name
     * @return
     */
    private Pessoa findBuscarPessoaUsandoNameQuery(Session session, String name) {
        TypedQuery<Pessoa> q2 =
                session.createNamedQuery("Pessoa.findPerfilsAndEnderecosByNome", Pessoa.class);
        q2.setParameter("nome", name);
        Pessoa person = q2.getSingleResult();
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
    private Pessoa findBuscarPessoaUsandoNativeQuery(Session session, String name) {
        TypedQuery<Pessoa> q2 =
                session.createNativeQuery("select * from TB_PESSOA where NO_NOME =:name", Pessoa.class);
        q2.setParameter("name", name);
        Pessoa person = q2.getSingleResult();
        return person;
    }


    /**
     * Buscando utilizando o Criteria Builder
     * Evitar Erros de sintaxe
     *
     * @param session
     * @param name
     * @return
     */
    private Pessoa findBuscarPessoaCriteria(Session session, String name) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Pessoa> q = cb.createQuery(Pessoa.class);
        Root<Pessoa> entityRoot = q.from(Pessoa.class);
        q.select(entityRoot);
        ParameterExpression<String> p = cb.parameter(String.class);
        q.where(cb.equal(entityRoot.get("nome"), name));
//        System.out.println();
        return session.createQuery(q).getSingleResult();
    }

    /**
     * Buscando utilizando o Criteria Builder
     * Evitar Erros de sintaxe
     *
     * @param session
     * @param name
     * @return
     */
    private Long countPessoaCriteria(Session session, String name) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        Root<Pessoa> entityRoot = q.from(Pessoa.class);
        q.select(cb.count(entityRoot));
        ParameterExpression<String> p = cb.parameter(String.class);
        q.where(cb.equal(entityRoot.get("nome"), name));
        System.out.println("QUERY : " + session.createQuery(q).getQueryString());
        return session.createQuery(q).getSingleResult();
    }


    /**
     * EFETUAR A BUSCAR COM QUERY
     * QUANDO NÃO POSSUI VALOR RETORNO NULL
     */
    @Test
    public void buscarPessoaComQuery() {
        try (Session session = factoryJpa.openSession()) {
            Pessoa pessoa1 = findBuscarPessoaAntigo(session, nome);
            System.out.println("Antigo: " + pessoa1);
        }
    }

    /**
     * EFETUAR A BUSCAR COM TYPEDQUERY
     * QUANDO NÃO POSSUI LANCA UMA NoResultException
     */
    @Test
    public void buscarPessoaComTypedQuery() {
        try (Session session = factoryJpa.openSession()) {
            Pessoa pessoa1 = findBuscarPessoaNovo(session, nome);
            System.out.println("Novo: " + pessoa1);
        }
    }

    /**
     * EFETUAR A BUSCAR COM NameQuery
     */
    @Test()
    public void findBuscarPessoaUsandoNameQuery() {
        try (Session session = factoryJpa.openSession()) {
            Pessoa pessoa1 = findBuscarPessoaUsandoNameQuery(session, nome);
            System.out.println("Perfils :" +pessoa1.getPerfils());
            System.out.println("Endereco "+pessoa1.getEnderecos());
            System.out.println("Novo: " + pessoa1);
        }
    }

    /**
     * EFETUAR A BUSCAR COM NameQuery
     */
    @Test()
    public void findBuscarPessoaUsandoNativeQuery() {
        try (Session session = factoryJpa.openSession()) {
            Pessoa pessoa1 = findBuscarPessoaUsandoNativeQuery(session, nome);
            System.out.println("Novo: " + pessoa1);
        }
    }

    /**
     * EFETUAR A BUSCAR COM NameQuery
     */
    @Test()
    public void findBuscarPessoaCriteria() {
        try (Session session = factoryJpa.openSession()) {
            Pessoa pessoa1 = findBuscarPessoaCriteria(session, nome);
            System.out.println("Perfils: " +pessoa1.getPerfils());
            System.out.println("Novo: " + pessoa1);
        }
    }


    /**
     * EFETUAR A BUSCAR COM NameQuery
     */
    @Test()
    public void findCountPessoa() {
        try (Session session = factoryJpa.openSession()) {
            Long qtd = countPessoaCriteria(session, nome);
            System.out.println("QTD: " + qtd);
        }
    }


    @Test
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
