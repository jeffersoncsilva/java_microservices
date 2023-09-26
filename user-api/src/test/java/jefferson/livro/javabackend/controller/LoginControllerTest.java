package jefferson.livro.javabackend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import api.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dtos.login.ErroLoginDTO;
import dtos.login.UserLoginDTO;
import dtos.login.UserTokenDTO;
import exceptions.UsuarioLoginOrPasswordWrongException;
import jefferson.livro.javabackend.service.LoginService;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @InjectMocks
    private LoginController sut;
    @Mock
    private LoginService serviceMock;

    private ObjectMapper mapper;

    private MockMvc mockMvc;
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void quando_usuarioComLoginESenhaValidoRealizaLogin_EntaoEDevolvidoUmToken() throws Exception{
        UserLoginDTO loginDTO = new UserLoginDTO();
        UserTokenDTO token = new UserTokenDTO();
        token.setToken("123");
        loginDTO.setCpf("123");
        loginDTO.setPassword("123");
        Mockito.when(serviceMock.realizaLogin(any(UserLoginDTO.class))).thenReturn(token);
        MvcResult results = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(loginDTO))
                ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        ApiResponse<UserTokenDTO> tokenResult = mapper.readValue(results.getResponse().getContentAsString(), new TypeReference<ApiResponse<UserTokenDTO>>(){});
        var data = tokenResult.getData();
        assertTrue(data instanceof UserTokenDTO);
        var tokenDto = (UserTokenDTO) data;
        assertEquals(token.getToken(), tokenDto.getToken());
    }

    @Test
    public void quando_usuarioComLoginESenhaInvalidoRealizaLogin_EntaoEDevolvidoUmErro() throws Exception{
        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setCpf("123");
        loginDTO.setPassword("123");
        Mockito.when(serviceMock.realizaLogin(any(UserLoginDTO.class))).thenThrow(new UsuarioLoginOrPasswordWrongException("Usuario ou senha invalidos"));
        MvcResult results = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(loginDTO))
                ).andExpect(MockMvcResultMatchers.status().isUnauthorized()).andReturn();
        ApiResponse<ErroLoginDTO> resp = mapper.readValue(results.getResponse().getContentAsString(), new TypeReference<ApiResponse<ErroLoginDTO>>(){});

        var data = resp.getData();
        assertTrue(data instanceof ErroLoginDTO);
        assertEquals("Erro na autenticacao", resp.getMessage());
        assertEquals(401, resp.getStatus());
    }
}