package com.stefanini.dao;

import com.stefanini.dao.abstracao.GenericDao;
import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;

/**
 * O Unico objetivo da Dao 
 * @author joaopedromilhome
 *
 */
public class EnderecoDao extends GenericDao<Endereco, Long> {

	public EnderecoDao() {
		super(Endereco.class);
	}

}
