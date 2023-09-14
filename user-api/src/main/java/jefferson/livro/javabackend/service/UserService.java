package jefferson.livro.javabackend.service;

import exceptions.UserNotFoundException;
import jefferson.livro.javabackend.dtoconverters.DTOConverter;
import jefferson.livro.javabackend.model.User;
import jefferson.livro.javabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import dtos.UserDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }
    public UserDTO findById(long userId) {
        User usuario = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User	not	found"));
        return DTOConverter.convert(usuario);
    }
    public UserDTO save(UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(DTOConverter.convert(userDTO));
        return DTOConverter.convert(user);
    }
    public UserDTO delete(long userId) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        userRepository.delete(user);
        return DTOConverter.convert(user);
    }
    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
            return DTOConverter.convert(user);
        }
        throw new UserNotFoundException();
    }
    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public UserDTO editUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        if (userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getTelefone() != null && !user.getTelefone().equals(userDTO.getTelefone())) {
            user.setTelefone(userDTO.getTelefone());
        }
        if (userDTO.getEndereco() != null && !user.getEndereco().equals(userDTO.getEndereco())) {
            user.setEndereco(userDTO.getEndereco());
        }
        user = userRepository.save(user);
        return DTOConverter.convert(user);
    }
}