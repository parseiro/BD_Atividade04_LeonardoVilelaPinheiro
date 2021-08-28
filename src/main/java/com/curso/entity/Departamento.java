package com.curso.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Table(name = "DEPARTAMENTOS")
@Entity
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Departamento {

    @Setter
    @Getter
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    Long id;

    @Column(name = "name", nullable = false)
    @NonNull
    @Getter
    @Setter
    private String name;

    @OrderBy("nome ASC")
    @OneToMany(mappedBy = "departamento")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    private List<Funcionario> funcionarios;
}
