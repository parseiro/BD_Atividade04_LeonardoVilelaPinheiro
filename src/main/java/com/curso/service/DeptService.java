package com.curso.service;

import com.curso.entity.Departamento;
import com.curso.entity.Funcionario;
import com.curso.repository.DepartamentoRepository;
import com.curso.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class DeptService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    DepartamentoRepository deptRepository;

    // Atividade 8.1
    // Criar um método na classe de serviço de departamento para salvar um departamento,
    // associar esse departamento a um funcionário e salvar esse funcionário em um mesmo
    // controle de transação.
    @Transactional
    public void newEmployeeAndDept(Departamento departamento, Funcionario funcionario) {

        var novoDepto = deptRepository.save(departamento);

        funcionarioRepository.save(funcionario);

        // simular uma exceção para fazer ROLLBACK
//        throw new IllegalArgumentException();
    }
}
