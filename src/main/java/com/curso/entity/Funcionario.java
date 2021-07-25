package com.curso.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@Table(name="FUNCIONARIOS")
public class Funcionario extends AbstractPersistable<Long> {
    @Column(name="name")
    private String nome;

    @Column(name = "dependentes", nullable = false)
    private int dependentes;

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @Digits(integer=5, fraction=2)
    @Column(name = "salario", nullable = false, precision = 5, scale = 2)
    private BigDecimal salario;

    @JoinColumn(nullable = false, name="departamento_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Departamento departamento;

    public int getDependentes() {
        return dependentes;
    }

    public Departamento getDepartamento() {
        return departamento;
    }
}
