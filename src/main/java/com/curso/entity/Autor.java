package com.curso.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "AUTORES")
public class Autor extends AbstractPersistable<Long> {
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;


    @ManyToMany
    @JoinTable(name = "LIVRO_AUTOR",
            joinColumns = @JoinColumn(name = "AUTOR_id"),
            inverseJoinColumns = @JoinColumn(name = "LIVRO_id"))
    private Set<Livro> livros;

    public void setLivros(Set<Livro> livros) {
        this.livros = livros;
    }

    public Set<Livro> getLivros() {
        return livros;
    }
}
