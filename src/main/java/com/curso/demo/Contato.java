package com.curso.demo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "CONTATOS")
public class Contato extends AbstractPersistable<Long> {
	@Column(name = "nome", length = 64, nullable = false)
	private String nome;
	
	@Column(name = "idade")
	private Integer idade;
	
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	private Date dtCadastro;
	
	@OneToMany(mappedBy= "proprietario")
	private Set<Endereco> endereco = new HashSet<>();

	public Set<Endereco> getEndereco() {
		return endereco;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

}
