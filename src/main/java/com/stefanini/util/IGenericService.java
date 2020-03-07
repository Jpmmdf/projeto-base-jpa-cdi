package com.stefanini.util;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IGenericService<T, I extends Serializable> {

	/**
	 * Salvar uma entidade
	 * @param entity
	 * @return
	 */
	T salvar(@Valid T entity);

	/**
	 * Atualizar uma entidade
	 * @param entity
	 * @return
	 */
	T atualizar(@Valid T entity);

	/**
	 *  Remover uma entidade
	 * @param id
	 */
	void remover(@Valid I id);

	/**
	 * Obter uma Lista
	 * @return
	 */
	Optional<List<T>> getList();

	/**
	 * Encontar um registro pelo id
	 * @param id
	 * @return
	 */
	Optional<T> encontrar(I id);

}
