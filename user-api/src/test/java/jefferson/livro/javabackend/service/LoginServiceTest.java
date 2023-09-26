package jefferson.livro.javabackend.service;

import dtos.login.UserLoginDTO;
import dtos.login.UserTokenDTO;
import exceptions.UsuarioLoginOrPasswordWrongException;
import jefferson.livro.javabackend.model.User;
import jefferson.livro.javabackend.repository.UserLoginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @InjectMocks
    private LoginService loginService;

    @Mock
    private UserLoginRepository loginRepository;

    @Test
    public void quandoLoginComCpfNulo_naoDeveChamarMetodoFindByCpfAndPassword(){
        UserLoginDTO dto = new UserLoginDTO();
        dto.setPassword("123");
        dto.setEmail("email@email.com");
        dto.setCpf(null);
        Mockito.when(loginRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(new User());
        UserTokenDTO token = loginService.realizaLogin(dto);
        Mockito.verify(loginRepository, Mockito.times(0)).findByCpfAndPassword(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(loginRepository, Mockito.times(1)).findByEmailAndPassword(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void quandoLoginComCpfNulo_naoDeveChamarMetodoFindByEmailAndPassword(){
        UserLoginDTO dto = new UserLoginDTO();
        dto.setPassword("123");
        dto.setEmail(null);
        dto.setCpf("123");
        Mockito.when(loginRepository.findByCpfAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(new User());
        UserTokenDTO token = loginService.realizaLogin(dto);
        Mockito.verify(loginRepository, Mockito.times(1)).findByCpfAndPassword(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(loginRepository, Mockito.times(0)).findByEmailAndPassword(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void quandoLoginComUsuarioNaoEncontrado_deveRetornarUmErro(){
        UserLoginDTO dto = new UserLoginDTO();
        dto.setPassword("123");
        dto.setEmail(null);
        dto.setCpf("123");
        Mockito.when(loginRepository.findByCpfAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        assertThrows(UsuarioLoginOrPasswordWrongException.class, () -> {
            UserTokenDTO token = loginService.realizaLogin(dto);
        });
    }
}