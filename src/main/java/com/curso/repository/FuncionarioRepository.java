package com.curso.repository;

import com.curso.entity.Departamento;
import com.curso.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    // Listar um funcionário pelo seu nome e quantidade de dependentes utilizando consulta por palavras-chaves.
    Optional<Funcionario> findByNomeAndDependentes(String nome, int dependentes);

    // Listar todos os funcionários de um determinado departamento por JPQL via @Query.
    @Query("select c from Funcionario c where c.departamento.name = :depto")
    Set<Funcionario> findFuncionarios(@Param("depto") String depto);

    //Listar o primeiro funcionário que tem o maior salário.
    Optional<Funcionario> findFirstByOrderBySalarioDesc();

    //Listar os 3 (três) primeiros funcionários que tem os maiores salários.
    List<Funcionario> findTop3ByOrderBySalarioDesc();


    //Listar os funcionários que não tem dependentes em ordem crescente de nome por JPQL via @Query.
    @Query("select c from Funcionario c where c.dependentes = 0 order by c.nome asc")
    Set<Funcionario> findFuncionariosSemDependentes();

    //Listar os funcionários que tem salário maior que um determinado valor por JPQL sobrescrevendo palavras-chaves @Query.
    @Query("select c from Funcionario c where c.salario > :grana")
    Collection<Funcionario> findFuncionariosSalarioMaiorQue(@Param("grana") BigDecimal grana);

    //Listar os funcionários que tem salário maior que um determinado valor por @Query com native query.
    @Query(value = "select * from Funcionarios where salario > :grana", nativeQuery = true)
    Collection<Funcionario> findFuncionariosSalarioMaiorQueNative(@Param("grana") BigDecimal grana);

    //Alterar a classe Funcionario e criar uma consulta para listar os funcionários com uma determinada quantidade de dependentes por @NamedQuery.
    @Query(name = "Funcionario.porDependentes")
    Collection<Funcionario> findFuncionariosComMaisDependentesQue(int dependentes);

    //Alterar a classe Funcionario e criar uma consulta para listar os funcionários que contenham em qualquer parte do seu nome um determinado nome por @NamedNativeQuery.
    @Query(name = "Funcionario.porParteDoNome")
    Funcionario findFuncionariosPorParteDoNome(String parte);

//Alterar a interface FuncionarioRepository para utilizar a @NamedQuery e @NamedNativeQuery realizadas nas questões 9 e 10 (respectivamente).

    // Atividade 7.1
    // Uma procedure que aumenta o salário de todos os funcionários em X porcento, onde X é um número inteiro.
    @Procedure(procedureName= "proc_raise_salaries")
    void raiseSalaries(Integer multiplier);
/*
CREATE PROCEDURE proc_raise_salaries(IN multiplier INTEGER)

BEGIN
    UPDATE funcionarios SET salario = salario * (1+multiplier/100);
END
 */


    // Atividade 7.2
    // Uma consulta que lista todos os funcionários de um determinado departamento que não possuam dependentes utilizando parâmetros nomeados.
    @Query("select c from Funcionario c where c.dependentes = 0 and c.departamento.id = :depto_id")
    List<Funcionario> findFuncionariosSemDependentesDeDepartamento(@Param("depto_id") Long depto_id);

    // Atividade 7.3
    // Uma instrução de update que troca todos os funcionários de um determinado departamento para outro departamento utilizando a anotação @Modifying.
    @Transactional
    @Modifying
    @Query("update Funcionario f set f.departamento.id = :new_dept_id where f.departamento.id = :old_dept_id")
    int changeTheirDept(@Param("old_dept_id") Long old_dept_id, @Param("new_dept_id") Long new_dept_id);
/*    @Query("update Funcionario f set f.departamento = :new_dept where f.departamento.id = :old_dept_id")
    int changeTheirDept(@Param("old_dept_id") Long old_dept_id, @Param("new_dept")Departamento new_dept);*/

    // Atividade 7.4
    // Uma instrução de delete que exclui todos os funcionários de um determinado departamento utilizando a anotação @Modifying.
    @Transactional
    @Modifying
    @Query("delete from Funcionario f where f.departamento = :dept")
    int deleteFuncByDept(@Param("dept") Departamento dept);
}