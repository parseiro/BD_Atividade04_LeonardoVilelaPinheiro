package com.curso.controller;

import com.curso.entity.Departamento;
import com.curso.entity.Funcionario;
import com.curso.repository.DepartamentoRepository;
import com.curso.repository.FuncionarioRepository;
import com.curso.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RestController
@RequestMapping("/funcionario")
@Validated
@Slf4j
public class FuncionarioController {
    @Autowired
    FuncionarioRepository employeeRepository;

    @Autowired
    DepartamentoRepository deptRepository;

    @Autowired
    DeptService deptService;

    @GetMapping("")
    public Iterable<Funcionario> listarDeptos() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Funcionario> pegarDepto(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }

    // Listar um funcionário pelo seu nome e quantidade de dependentes utilizando consulta por palavras-chaves.
    @GetMapping("/findByNomeAndDependentes/{nome}/{dependentes}")
    public Optional<Funcionario> findByNomeAndDependentes(@PathVariable String nome, @PathVariable int dependentes) {
        return employeeRepository.findByNomeAndDependentes(nome, dependentes);
    }

    // Listar todos os funcionários de um determinado departamento por JPQL via @Query.
    @GetMapping("/findFuncionarios/{departamento}")
    public Set<Funcionario> findFuncionarios(@PathVariable String departamento) {
        return employeeRepository.findFuncionarios(departamento);
    }

    //Listar o primeiro funcionário que tem o maior salário.
    @GetMapping("/maiorSalario")
    public Optional<Funcionario> maiorSalario() {
        return employeeRepository.findFirstByOrderBySalarioDesc();
    }

    //Listar os 3 (três) primeiros funcionários que tem os maiores salários.
    @GetMapping("/3maioresSalarios")
    public List<Funcionario> tresMaioresSalarios() {
        return employeeRepository.findTop3ByOrderBySalarioDesc();
    }

    //Listar os funcionários que não tem dependentes em ordem crescente de nome por JPQL via @Query.
    @GetMapping("/funcionariosSemDependentes")
    public Set<Funcionario> funcionariosSemDependentes() {
        return employeeRepository.findFuncionariosSemDependentes();
    }

    //Listar os funcionários que tem salário maior que um determinado valor por JPQL sobrescrevendo palavras-chaves @Query.
    @GetMapping("/salarioMaiorQue/{valor}")
    public Collection<Funcionario> funcionariosSalariosMaioresQue(@PathVariable BigDecimal valor) {
        return employeeRepository.findFuncionariosSalarioMaiorQue(valor);
    }

    //Listar os funcionários que tem salário maior que um determinado valor por JPQL sobrescrevendo palavras-chaves @Query.
    @GetMapping("/salarioMaiorQueNATIVO/{valor}")
    public Collection<Funcionario> funcionariosSalariosMaioresQueNATIVO(@PathVariable BigDecimal valor) {
        return employeeRepository.findFuncionariosSalarioMaiorQueNative(valor);
    }

    //Alterar a classe Funcionario e criar uma consulta para listar os funcionários com uma determinada quantidade de dependentes por @NamedQuery.
    @GetMapping("/maisQueXDependentes/{quantos}")
    public Collection<Funcionario> maisQueXDependentes(@PathVariable int quantos) {
        return employeeRepository.findFuncionariosComMaisDependentesQue(quantos);
    }

    //Alterar a classe Funcionario e criar uma consulta para listar os funcionários que contenham em qualquer parte do seu nome um determinado nome por @NamedNativeQuery.
    @GetMapping("/procurarPorParteDoNome/{parte}")
    public Funcionario procurarPorParteDoNome(@PathVariable String parte) {
        return employeeRepository.findFuncionariosPorParteDoNome(parte);
    }

    // Atividade 7.1
    @GetMapping("/raiseSalaries/{quanto}")
    public boolean raiseSalaries(@PathVariable Integer quanto) {
//        var porcentagem = (BigDecimal.ONE.add(BigDecimal.valueOf(quanto/100))).setScale(2);
//        log.info("Aumentando o valor em " + quanto);
        employeeRepository.raiseSalaries(quanto);
//        return result > 0 ? true : false;
        return true;
    }

    // Atividade 7.2
    @GetMapping("/funcionariosSemDependentes/{deptName}")
    public Iterable<Funcionario> findEmployeeWithoutDependentsByDept(@PathVariable String deptName) {
        var dept = deptRepository.findByName(deptName).get();
        return employeeRepository.findFuncionariosSemDependentesDeDepartamento(dept.getId());
    }

    // Atividade 7.3
    @GetMapping("/mudarDepartamento/{oldDeptName}/{newDeptName}")
    public boolean changeDept(@PathVariable String oldDeptName, @PathVariable String newDeptName) {
        var oldDept = deptRepository.findByName(oldDeptName).get();
        var newDept = deptRepository.findByName(newDeptName).get();
        var result = employeeRepository.changeTheirDept(oldDept.getId(), newDept.getId());
        return result > 0;
    }

    // Atividade 7.4
    @GetMapping("/deleteAllInDept/{deptName}")
    public boolean deleteAllInDept(@PathVariable String deptName) {
        var dept = deptRepository.findByName(deptName).get();
        var result = employeeRepository.deleteFuncByDept(dept);
        return result > 0;
    }

    // Atividade 8.1
    @GetMapping("/transacional")
    public void transacional() {
        Departamento departamento = Departamento.builder()
                .name("Departamento Secreto")
                .build();
        Funcionario funcionario = Funcionario.builder()
                .nome("Clark Kent")
                .departamento(departamento)
                .cargo("Super herói")
                .dependentes(1)
                .salario(BigDecimal.valueOf(0)) // trabalha de graça para o bem da humanidade
                .build();
        deptService.newEmployeeAndDept(departamento, funcionario);
    }
}
