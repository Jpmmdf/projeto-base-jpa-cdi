package com.stefanini.servico;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.stefanini.dao.PessoaDao;
import com.stefanini.model.Pessoa;
import com.stefanini.util.IGenericService;

public class PessoaServico implements IGenericService<Pessoa, Long>{

	

	@Inject
	private PessoaDao pessoaDao;
	
	
	
	@Override
	public Pessoa salvar(@Valid Pessoa pessoa) {
		System.out.println("Passei aqui");
		pessoa.setNome(pessoa.getNome() + "treinamento");
		if(pessoa.getNome().toLowerCase().contains("a")) {
			pessoa.setSituacao(Boolean.FALSE);
		}
		return pessoaDao.salvar(pessoa);
	}

	@Override
	public Pessoa atualizar(@Valid Pessoa entity) {
		// TODO Auto-generated method stub
		return pessoaDao.atualizar(entity);
	}

	@Override
	public void remover(Long id) {
		pessoaDao.remover(id);
		
	}

	@Override
	public List<Pessoa> getList() {
		// TODO Auto-generated method stub
		return pessoaDao.getList();
	}

	@Override
	public Pessoa encontrar(Long id) {
		// TODO Auto-generated method stub
		return pessoaDao.encontrar(id);
	}

}
