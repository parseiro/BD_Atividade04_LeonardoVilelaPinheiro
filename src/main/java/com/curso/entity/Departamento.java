package com.curso.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Table(name = "DEPARTAMENTOS")
@Entity
public class Departamento extends AbstractPersistable<Long> {
    @Column(name = "name", nullable = false)
    private String nome;

    @OrderBy("nome ASC")
    @OneToMany(mappedBy = "departamento")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Funcionario> funcionarios;

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
