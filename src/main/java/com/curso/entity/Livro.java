package com.curso.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LIVROS")
public class Livro extends AbstractPersistable<Long> {
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;


    @ManyToMany(mappedBy = "livros")
    private Set<Autor> autores;

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Set<Autor> getAutores() {
        return autores;
    }
}
