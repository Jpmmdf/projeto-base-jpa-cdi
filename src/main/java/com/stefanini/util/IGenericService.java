package com.stefanini.util;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

public interface IGenericService<T, I extends Serializable> {

	/**
	 * Salvar uma entidade
	 * @param entity
	 * @return
	 */
	T salvar(@Valid T entity);

	/**
	 * 
	 * @param entity
	 * @return
	 */
	T atualizar(@Valid T entity);

	void remover(I id);

	List<T> getList();

	T encontrar(I id);

}
