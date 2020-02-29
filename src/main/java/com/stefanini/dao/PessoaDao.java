package com.stefanini.dao;

import javax.validation.Valid;

import com.stefanini.dao.abstracao.GenericDao;
import com.stefanini.model.Pessoa;

public class PessoaDao extends GenericDao<Pessoa, Long> {

//	@Override
//	public Pessoa salvar(@Valid Pessoa entity) {
//		Pessoa pessoaSalva = null;
//		try {
//			pessoaSalva = super.salvar(entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("DEU RUIM");
//		}
//		return pessoaSalva;
//	}

}
