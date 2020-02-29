package com.stefanini.dao.abstracao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;

import com.stefanini.util.IGenericService;

public abstract class GenericDao<T, I extends Serializable> implements IGenericService<T, I>{



	@Inject
	protected EntityManager entityManager;

	private Class<T> persistedClass;

	protected GenericDao() {
	}

	protected GenericDao(Class<T> persistedClass) {
		this();
		this.persistedClass = persistedClass;
	}

	/**
	 * Salvar Um entidade
	 */
	public T salvar(@Valid T entity) {
		EntityTransaction t = iniciarTransacao();
		entityManager.persist(entity);
		finalizarTransacao(t);
		return entity;
	}

	public T atualizar(@Valid T entity) {
		EntityTransaction t = iniciarTransacao();
		entityManager.merge(entity);
		finalizarTransacao(t);
		return entity;
	}

	

	public void remover(I id) {
		T entity = encontrar(id);
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		T mergedEntity = entityManager.merge(entity);
		entityManager.remove(mergedEntity);
		finalizarTransacao(tx);
	}

	public List<T> getList() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(persistedClass);
		query.from(persistedClass);
		return entityManager.createQuery(query).getResultList();
	}

	public T encontrar(I id) {
		return entityManager.find(persistedClass, id);
	}
	
	
	private void finalizarTransacao(EntityTransaction t) {
		entityManager.flush();
		t.commit();
	}

	private EntityTransaction iniciarTransacao() {
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
		return t;
	}
}
