package com.stefanini.dao.abstracao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;

import com.stefanini.util.IGenericService;

/**
 * @author joaopedromilhome
 *
 * @param <T>
 * @param <I>
 */
public abstract class GenericDao<T, I extends Serializable> implements IGenericService<T, I>{



	@Inject
	protected EntityManager entityManager;

	/**
	 * Classe que será efetuada as transacoes
	 */
	private Class<T> classe;

	protected GenericDao() {
	}

	protected GenericDao(Class<T> classe) {
		this();
		this.classe = classe;
	}

	/**
	 * @valid serve para validar a entidade antes de entrar no metodo, olhar o conceito de BEAN VALIDATION
	 * Sempre que for executar uma DML é necessario abrir uma transacao e fecha-la, pois senão a operacao não será comitada
	 */
	public T salvar(@Valid T entity) {
		EntityTransaction t = iniciarTransacao();
		entityManager.persist(entity);
		finalizarTransacao(t);
		return entity;
	}
	/**
	 * @valid serve para validar a entidade antes de entrar no metodo, olhar o conceito de BEAN VALIDATION
	 * Sempre que for executar uma DML é necessario abrir uma transacao e fecha-la, pois senão a operacao não será comitada
	 */
	public T atualizar(@Valid T entity) {
		EntityTransaction t = iniciarTransacao();
		entityManager.merge(entity);
		finalizarTransacao(t);
		return entity;
	}

	
	/**
	 * Sempre que for executar uma DML é necessario abrir uma transacao e fecha-la, pois senão a operacao não será comitada
	 */
	public void remover(I id) {
		T entity = encontrar(id).get();
		EntityTransaction t = iniciarTransacao();
		getEntityManager().remove(entity);
		finalizarTransacao(t);
	}

	/**
	 * Não precisa de Transacao para efetuar DQL
	 */
	public Optional<List<T>> getList() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(classe);
		query.from(classe);
		return Optional.of(getEntityManager().createQuery(query).getResultList());
	}
	/**
	 * Não precisa de Transacao para efetuar DQL
	 */
	public Optional<T> encontrar(I id) {
		return Optional.ofNullable(getEntityManager().find(classe, id));
	}
	
	
	/**
	 * Comitar a transcao e liberar o 
	 * @param t
	 */
	private void finalizarTransacao(EntityTransaction t) {
		// SINCRONIZAR O DADOS COM O BANCO
		getEntityManager().flush();
		t.commit();
	}

	/**
	 * Abrir uma transcao com o banco
	 * @return
	 */
	private EntityTransaction iniciarTransacao() {
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
		return t;
	}

	/**
	 * Obter o EntityManager
	 * @return
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	
}
