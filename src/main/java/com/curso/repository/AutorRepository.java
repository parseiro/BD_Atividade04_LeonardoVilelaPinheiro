package com.curso.repository;

import com.curso.entity.Autor;
import org.springframework.data.repository.CrudRepository;

public interface AutorRepository extends CrudRepository<Autor, Long> {
}
