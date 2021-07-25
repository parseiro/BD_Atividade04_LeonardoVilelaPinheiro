package com.curso.controller;

import com.curso.entity.Departamento;
import com.curso.entity.Funcionario;
import com.curso.repository.DepartamentoRepository;
import com.curso.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
@RequestMapping("/funcionario")
@Validated
public class FuncionarioController {
    @Autowired
    FuncionarioRepository repository;

    @GetMapping("")
    public Iterable<Funcionario> listarDeptos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Funcionario> pegarDepto(@PathVariable Long id) {
        return repository.findById(id);
    }
}
