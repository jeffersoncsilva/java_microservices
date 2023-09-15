package jefferson.livro.javabackend.service;

import dtos.UserDTO;
import jefferson.livro.javabackend.dtoconverters.DTOConverter;
import jefferson.livro.javabackend.model.User;
import jefferson.livro.javabackend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    public static User getUser(Integer id, String nome,String cpf){
        User user = new User();
        user.setId(id);
        user.setNome(nome);
        user.setCpf(cpf);
        user.setEndereco("Endereco");
        user.setTelefone("5431");
        return user;
    }

    @Test
    public void testListAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(getUser(1, "User 1", "123"));
        users.add(getUser(2, "User 2", "456"));
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<UserDTO> usersReturns = userService.getAll();
        Assertions.assertEquals(2, usersReturns.size());
    }

    @Test
    public void testSaveUser(){
        User uDb = getUser(1, "User 1", "123");
        UserDTO dto = DTOConverter.convert(uDb);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(uDb);
        UserDTO user = userService.save(dto);
        Assertions.assertEquals("User 1", user.getNome());
        Assertions.assertEquals("123", user.getCpf());
    }

    @Test
    public void testEditUser(){
        User userDb = getUser(1, "User 1", "123");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userDb));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userDb);

        UserDTO userDTO = DTOConverter.convert(userDb);
        userDTO.setEndereco("Novo endereco");
        userDTO.setTelefone("123456");
        UserDTO user = userService.editUser(1L, userDTO);
        Assertions.assertEquals("Novo endereco", user.getEndereco());
        Assertions.assertEquals("123456", user.getTelefone());
    }

}