package com.curso;

import com.curso.entity.Departamento;
import com.curso.entity.Funcionario;
import com.curso.repository.DepartamentoRepository;
import com.curso.repository.FuncionarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
public class BdAtividade07LeonardoVilelaPinheiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(BdAtividade07LeonardoVilelaPinheiroApplication.class, args);
    }

    @Bean
    public CommandLineRunner cargaDepartamentoFuncionario(DepartamentoRepository deptRepository, FuncionarioRepository funcRepository) {
        return args -> {
            log.info("Criando o banco de dados de desenvolvimento");
            funcRepository.deleteAll();
            deptRepository.deleteAll();


            { // RH
                var RH = deptRepository.save(Departamento.builder().name("RH").build());

                funcRepository.save(Funcionario.builder().nome("Maicon José").departamento(RH).cargo("Recrutador").dependentes(3).salario(BigDecimal.valueOf(1000)).build());
                funcRepository.save(Funcionario.builder().nome("Tânia Marta").departamento(RH).cargo("Secretária").dependentes(0).salario(BigDecimal.valueOf(1000)).build());
                funcRepository.save(Funcionario.builder().nome("Johny Salgado").departamento(RH).cargo("Gerente de RH").dependentes(1).salario(BigDecimal.valueOf(1000)).build());
                funcRepository.save(Funcionario.builder().nome("Maicon José").departamento(RH).cargo("Recrutador").dependentes(2).salario(BigDecimal.valueOf(1000)).build());
                funcRepository.save(Funcionario.builder().nome("Tereza Matias").departamento(RH).cargo("Gerente de RH").dependentes(0).salario(BigDecimal.valueOf(1000)).build());
            }

            {
                var jurídico = deptRepository.save(Departamento.builder().name("Jurídico").build());

                funcRepository.save(Funcionario.builder().nome("Pereirão").departamento(jurídico).cargo("Advogado").dependentes(0).salario(BigDecimal.valueOf(2500.50)).build());
                funcRepository.save(Funcionario.builder().nome("Pereirinha").departamento(jurídico).cargo("Advogado").dependentes(1).salario(BigDecimal.valueOf(2500.50)).build());
                funcRepository.save(Funcionario.builder().nome("Dunha Chiclete").departamento(jurídico).cargo("Advogado").dependentes(2).salario(BigDecimal.valueOf(2500.50)).build());
                funcRepository.save(Funcionario.builder().nome("Michel Phelps").departamento(jurídico).cargo("Advogado").dependentes(3).salario(BigDecimal.valueOf(2500.50)).build());
            }

            deptRepository.save(Departamento.builder().id(3L).name("TI").build());
            deptRepository.save(Departamento.builder().id(4L).name("Finceiro").build());
            deptRepository.save(Departamento.builder().id(5L).name("Vendas").build());
            deptRepository.save(Departamento.builder().id(6L).name("Compras").build());
        };
    }
}
