package com.curso.repository;

import com.curso.entity.Livro;
import org.springframework.data.repository.CrudRepository;

public interface LivroRepository extends CrudRepository<Livro, Long> {
}
