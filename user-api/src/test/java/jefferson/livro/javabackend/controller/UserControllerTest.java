package jefferson.livro.javabackend.controller;

import dtos.UserDTO;
import jefferson.livro.javabackend.dtoconverters.DTOConverter;
import jefferson.livro.javabackend.service.UserService;
import jefferson.livro.javabackend.service.UserServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testListUsers() throws Exception{
        List<UserDTO> users = new ArrayList<>();
        users.add(DTOConverter.convert(UserServiceTest.getUser(1, "Nome 1", "123")));
        Mockito.when(userService.getAll()).thenReturn(users);
        MvcResult restuls = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resp = restuls.getResponse().getContentAsString();
        Assertions.assertEquals("[{\"nome\":\"Nome 1\",\"cpf\":\"123\",\"endereco\":\"Endereco\",\"email\":null,\"telefone\":\"5431\",\"dataCadastro\":null,\"key\":null}]", resp);
    }

}