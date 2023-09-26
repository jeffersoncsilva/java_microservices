package jefferson.livro.javabackend.repository;

import jefferson.livro.javabackend.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = {UserLoginRepositoryTest.DataSourceInitializer.class})
class UserLoginRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:latest");

    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext,
                    "spring.datasource.url="+database.getJdbcUrl(),
                    "spring.datasource.username="+database.getUsername(),
                    "spring.datasource.password="+database.getPassword());
        }
    }

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Test
    public void quando_usuarioLoginPorCpfENaoExisteNoBanco_deveRetornarUsuarioNulo(){
        Cpf cpf = new Cpf("747.137.420-90");
        String senha = "senha";
        var user = userLoginRepository.findByCpfAndSenha(cpf, senha);
        assertNull(user);
    }

    @Test
    public void quando_usuarioLoginPorEmailENaoExisteNoBanco_deveRetornarUsuarioNulo(){
        Email email = new Email("email@email.com");
        String senha = "senha";
        var user = userLoginRepository.findByEmailAndSenha(email, senha);
        assertNull(user);
    }

    @Test
    @Transactional
    public void quando_usuarioExisteNoBancoELoginPorCPf_deveRetornarUsuarioComSeusDados(){
        Cpf cpf = new Cpf("747.137.420-90");
        String senha = "senha";
        Endereco endereco = new Endereco();
        endereco.setCep("74000000");
        endereco.setCidade("Goiânia");
        endereco.setEstado("GO");
        endereco.setRua("Rua 1");
        endereco.setNumero("1");
        endereco.setComplemento("Casa");
        endereco.setBairro("Centro");

        userLoginRepository.save(new User(1, "nome", senha, cpf, endereco, new Email("email@email.com"), new Telefone("(62) 99999-9999"), LocalDateTime.now()));
        var user = userLoginRepository.findByCpfAndSenha(cpf, senha);
        assertNotNull(user);
        assertEquals(cpf, user.getCpf());
        assertEquals(senha, user.getSenha());
    }

    @Test
    @Transactional
    public void quando_usuarioExisteNoBancoELoginPorEmail_deveRetornarUsuarioComSeusDados(){
        Email email = new Email("email@email.com");
        String senha = "senha";
        Cpf cpf = new Cpf("747.137.420-90");
        Endereco endereco = new Endereco();
        endereco.setCep("74000000");
        endereco.setCidade("Goiânia");
        endereco.setEstado("GO");
        endereco.setRua("Rua 1");
        endereco.setNumero("1");
        endereco.setComplemento("Casa");
        endereco.setBairro("Centro");

        userLoginRepository.save(new User(1, "nome", senha, cpf, endereco, email, new Telefone("(62) 99999-9999"), LocalDateTime.now()));
        var user = userLoginRepository.findByEmailAndSenha(email, senha);
        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertEquals(senha, user.getSenha());
    }
}