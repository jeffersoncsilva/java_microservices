package jefferson.livro.javabackend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dtos.user.UserDTO;
import jefferson.livro.javabackend.Utils;
import jefferson.livro.javabackend.dtoconverters.DTOConverter;
import jefferson.livro.javabackend.service.UserService;
import jefferson.livro.javabackend.service.UserServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void quando_listarTodosUsuarios_deveRetornarListaComTodosUsuarios() throws Exception{
        List<UserDTO> users = new ArrayList<>();
        users.add(DTOConverter.convert(UserServiceTest.getUser(1, "Nome 1", "123")));
        Mockito.when(userService.pegaTodosUsuarios()).thenReturn(users);
        MvcResult restuls = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        var resp = mapper.readValue(restuls.getResponse().getContentAsString(), new TypeReference<List<UserDTO>>() {});
        assertEquals(resp.size(), resp.size());
    }

    @Test
    public void quando_buscarUsuarioPorId_deveRetornarEsseUsuarioComIdEspecificado() throws Exception{
        UserDTO user = DTOConverter.convert(UserServiceTest.getUser(1, "Nome 1", "123"));
        Mockito.when(userService.buscaUsuarioPorId(1L)).thenReturn(user);
        MvcResult restuls = mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        var resp = mapper.readValue(restuls.getResponse().getContentAsString(), UserDTO.class);
        assertEquals(resp, user);
    }

    @Test
    public void quando_cadastrarNovoUsuario_deveRetornarEsseUsuarioCadastrado() throws Exception{
        UserDTO user = DTOConverter.convert(Utils.getUser("924.218.490-04"));
        user.setSenha("senha");
        Mockito.when(userService.cadastraNovoUsuario(user)).thenReturn(user);
        MvcResult restuls = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        var resp = mapper.readValue(restuls.getResponse().getContentAsString(), UserDTO.class);
        assertEquals(resp, user);
    }

    @Test
    public void quando_apagarUmUsuario_deveRetornarResultadoOK() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }
}