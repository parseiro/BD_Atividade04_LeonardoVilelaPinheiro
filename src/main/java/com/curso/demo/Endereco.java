package com.curso.demo;

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "ENDERECOS")
public class Endereco extends AbstractPersistable<Long> {
	public enum TipoEndereco {
		RESIDENCIAL, COMERCIAL
	}

	@Column(name = "tipo", length = 32, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipoEndereco;

	@Column(name = "logradouro", length = 64, nullable = false)
	private String logradouro;

	@Column(name = "cidade", length = 64, nullable = false)
	private String cidade;

	@Column(name = "estado", length = 2, nullable = false)
	private String estado;

	@JoinColumn(name = "contato_id", nullable = false)
	@ManyToOne(optional = false)
	private Contato proprietario;

	public Contato getProprietario() {
		return proprietario;
	}

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}
}
