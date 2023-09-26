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

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = {UserRepositoryTest.DataSourceInitializer.class})
class UserRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:latest");

    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext,
                    "spring.datasource.url="+database.getJdbcUrl(),
                    "spring.datasource.username="+database.getUsername(),
                    "spring.datasource.password="+database.getPassword());
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void quando_listarTodosUsuariosComBancoSomenteCom3Usuarios_deveRetornarUmaListaCom3UsuariosApenas(){
        var lista = userRepository.findAll();
        assertEquals(3, lista.size());
    }

    @Test
    @Transactional
    public void quando_inserir2UsuariosComBancoVazio_deveRetornar2Usuarios(){
        userRepository.save(getUser("747.137.420-90"));
        userRepository.save(getUser("985.724.350-93"));
        var lista = userRepository.findAll();
        assertEquals(5, lista.size());
    }

    @Test
    @Transactional
    public void quando_editarUmUsuario_deveRetornarOUsuarioEditado(){
        var user = getUser("373.226.500-50");
        userRepository.save(user);
        user.setNome("Jefferson");
        userRepository.save(user);
        var lista = userRepository.findAll();
        assertEquals(4, lista.size());
        assertEquals("Jefferson", lista.get(3).getNome());
    }

    @Test
    @Transactional
    public void quando_apgarUmUsuario_naoDeveRetornarErroAoApagarUsuario(){
        var user = getUser("373.226.500-50");
        userRepository.save(user);
        userRepository.delete(user);
        var lista = userRepository.findAll();
        assertEquals(3, lista.size());
    }

    @Test
    public void quando_buscarPeloNomeTeste_deveRetornar3UsuariosDeTeste(){
        var lista = userRepository.queryByNomeLike("Teste");
        assertEquals(1, lista.size());
    }
    public static User getUser(String cpf){
        var user = new User();
        user.setNome("Jefferson");
        user.setCpf(new Cpf(cpf));
        user.setEmail(new Email("email."+cpf+"com@email.com"));
        user.setSenha("senha");
        user.setDataCadastro(LocalDateTime.now());
        Endereco endereco = new Endereco();
        endereco.setCep("35170000");
        endereco.setRua("Rua 1");
        endereco.setNumero("123");
        endereco.setBairro("Bairro 1");
        endereco.setCidade("Cidade 1");
        endereco.setEstado("MG");
        endereco.setComplemento("complemento");
        user.setEndereco(endereco);
        user.setTelefone(new Telefone("(33) 9999-9999"));
        return user;
    }

}