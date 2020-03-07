package com.stefanini.servico;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.model.Endereco;
import com.stefanini.util.IGenericService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * @author joaopedromilhome
 *
 */
public class EnderecoServico implements IGenericService<Endereco, Long> {
	
	@Inject
	private EnderecoDao dao;


	@Override
	public Endereco salvar(@Valid Endereco entity) {
		return dao.salvar(entity);
	}

	@Override
	public Endereco atualizar(@Valid Endereco entity) {
		return dao.atualizar(entity);
	}

	@Override
	public void remover(Long id) {
	dao.remover(id);
	}

	@Override
	public Optional<List<Endereco>> getList() {
		return Optional.empty();
	}

	@Override
	public Optional<Endereco> encontrar(Long id) {
		return dao.encontrar(id);
	}
}
