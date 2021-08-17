package com.curso.repository;

import com.curso.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
