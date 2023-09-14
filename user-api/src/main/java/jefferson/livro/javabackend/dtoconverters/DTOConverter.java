package jefferson.livro.javabackend.dtoconverters;

import dtos.UserDTO;
import jefferson.livro.javabackend.model.User;

public class DTOConverter {
    public static UserDTO convert(User u){
        UserDTO dto = new UserDTO();
        dto.setCpf(u.getCpf());
        dto.setEmail(u.getEmail());
        dto.setNome(u.getNome());
        dto.setEndereco(u.getEndereco());
        dto.setTelefone(u.getTelefone());
        dto.setDataCadastro(u.getDataCadastro());
        dto.setKey(u.getKey());
        return dto;
    }
    public static User convert(UserDTO dto){
        User user = new User();
        user.setDataCadastro(dto.getDataCadastro());
        user.setCpf(dto.getCpf());
        user.setNome(dto.getNome());
        user.setEndereco(dto.getEmail());
        user.setEndereco(dto.getEndereco());
        user.setTelefone(dto.getTelefone());
        user.setKey(dto.getKey());
        return user;
    }
}
