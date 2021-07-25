package com.curso.controller;

import com.curso.entity.Departamento;
import com.curso.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
@RequestMapping("/departamento")
@Validated
public class DeptoController {
    @Autowired
    DepartamentoRepository repository;

    @GetMapping("")
    public Iterable<Departamento> listarDeptos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Departamento> pegarDepto(@PathVariable Long id) {
        return repository.findById(id);
    }


}
