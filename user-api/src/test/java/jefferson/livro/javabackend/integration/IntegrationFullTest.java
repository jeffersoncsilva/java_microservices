package jefferson.livro.javabackend.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dtos.user.CpfDTO;
import dtos.user.UserDTO;
import jefferson.livro.javabackend.Utils;
import jefferson.livro.javabackend.dtoconverters.DTOConverter;
import jefferson.livro.javabackend.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationFullTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;

    private ObjectMapper mapper;

    @Container
    private static final PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:latest");

    static{
        database.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamic){
        dynamic.add("spring.datasource.url", database::getJdbcUrl);
        dynamic.add("spring.datasource.username", database::getUsername);
        dynamic.add("spring.datasource.password", database::getPassword);
    }

    @BeforeEach
    public void setup(){
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @Order(value = 1)
    void testConnectionToDatabase(){
        assertNotNull(repository);
    }

    @Test
    @Order(value = 2)
    @Sql("/integration_full_test/data.sql")
    @Transactional
    public void quando_solicitacaoParaListarTodosUsuarios_deveRetornarUmaListaComTodosUsuarios() throws Exception{
        MvcResult restul = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertNotNull(restul);
        assertNotNull(restul.getResponse());
        assertNotNull(restul.getResponse().getContentAsString());
        List<UserDTO> result = mapper.readValue(restul.getResponse().getContentAsString(), new TypeReference<List<UserDTO>>() {});

        assertEquals(4, result.size());
    }

    @Test
    public void quandoInserirUmUsuario_deveRetornarOUsuarioInserido() throws Exception{
        UserDTO user = DTOConverter.convert(Utils.getUser("373.226.500-50"));
        user.setSenha("senha");
        MvcResult restuls = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        var resp = mapper.readValue(restuls.getResponse().getContentAsString(), UserDTO.class);
        assertEquals(user, resp);
    }

    @Test
    @Sql("/integration_full_test/data.sql")
    @Transactional
    public void quandoBuscarUsuarioPorId_deveRetornarUmUsuarioSeExistirNoBanco() throws Exception{
        MvcResult restuls = mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        var resp = mapper.readValue(restuls.getResponse().getContentAsString(), UserDTO.class);
        assertEquals(new CpfDTO("267.380.310-68"), resp.getCpf());
    }

    @Test
    @Sql("/integration_full_test/data.sql")
    @Transactional
    public void quandoBuscarUsuarioPorId_deveRetornarNuloQuandoUsuarioNaoExisteNoBanco() throws Exception{
        MvcResult restuls = mockMvc.perform(MockMvcRequestBuilders.get("/user/50"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        assertEquals(404, restuls.getResponse().getStatus());
        assertTrue(restuls.getResponse().getContentAsString().isEmpty());
    }
}
