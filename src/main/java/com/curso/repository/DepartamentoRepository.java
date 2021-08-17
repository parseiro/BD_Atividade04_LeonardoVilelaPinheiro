package com.curso.repository;

import com.curso.entity.Departamento;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartamentoRepository extends CrudRepository<Departamento, Long> {
    //Listar o primeiro departamento cadastrado.
    Optional<Departamento> findFirstByOrderByIdAsc();

    Optional<Departamento> findByName(String name);


}
