package com.stefanini.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class PessoaTest {
	
	private Validator validator;
	 
	  @Before
	  public void setUp() {
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    this.validator = factory.getValidator();
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
