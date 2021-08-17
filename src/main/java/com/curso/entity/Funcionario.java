package com.curso.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@Table(name = "FUNCIONARIOS")
@NamedQueries({
        //Alterar a classe Funcionario e criar uma consulta para listar os funcionários com uma determinada quantidade de dependentes por @NamedQuery.
        @NamedQuery(name = "Funcionario.porDependentes", query = "select distinct f from Funcionario f where f.dependentes > ?1"),

})
@NamedNativeQueries({
        //Alterar a classe Funcionario e criar uma consulta para listar os funcionários que contenham em qualquer parte do seu nome um determinado nome por @NamedNativeQuery.
        @NamedNativeQuery(name = "Funcionario.porParteDoNome",
                query = "select * from funcionarios where name like CONCAT('%', ?1, '%')",
                resultClass = Funcionario.class)
})
/*@NamedStoredProcedureQuery(
        name = "Funcionario.raiseSalaries",
        procedureName = "proc_raise_salaries",
        parameters = {
                @StoredProcedureParameter(
                        mode = ParameterMode.IN,
                        name = "multiplier",
                        type = Integer.class
                )
        }
)*/
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Funcionario implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    Long id;

    @Column(name = "name")
    @NonNull
    @Getter
    @Setter
    private String nome;

    @Column(name = "dependentes", nullable = false)
    @NonNull
    @Getter
    @Setter
    private int dependentes;

    @Column(name = "cargo", nullable = false, length = 50)
    @NonNull
    @Getter
    @Setter
    private String cargo;

    @Digits(integer = 5, fraction = 2)
    @Column(name = "salario", nullable = false, precision = 7, scale = 2)
    @NonNull
    @Getter
    @Setter
    private BigDecimal salario;

    @JoinColumn(nullable = false, name = "departamento_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NonNull
    @Getter
    @Setter
    private Departamento departamento;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }

}
