package repository;

import com.curso.repository.FuncionarioRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({DBUnitExtension.class, SpringExtension.class})
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
//@ActiveProfiles("test")
@Slf4j
public class FuncionarioRepositoryTest {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Atividade 7.2
    @Test
    @DataSet({"funcionarios.yml", "departamentos.yml"})
    public void testFindFuncionariosSemDependentesDeDepartamento() {

    }
}
