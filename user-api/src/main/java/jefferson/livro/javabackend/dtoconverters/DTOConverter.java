package jefferson.livro.javabackend.dtoconverters;

import dtos.user.*;
import jefferson.livro.javabackend.model.*;

public class DTOConverter {
    public static UserDTO convert(User u){
        UserDTO dto = new UserDTO();
        dto.setCpf(convert(u.getCpf()));
        dto.setEmail(convert(u.getEmail()));
        dto.setNome(u.getNome());
        dto.setEndereco(convert(u.getEndereco()));
        dto.setTelefone(convert(u.getTelefone()));
        dto.setDataCadastro(u.getDataCadastro());
        return dto;
    }
    public static User convert(UserDTO dto){
        User user = new User();
        user.setDataCadastro(dto.getDataCadastro());
        user.setNome(dto.getNome());
        user.setCpf(convert(dto.getCpf()));
        user.setEmail(convert(dto.getEmail()));
        user.setTelefone(convert(dto.getTelefone()));
        user.setEndereco(convert(dto.getEndereco()));
        return user;
    }
    public static Cpf convert(CpfDTO dto){
        return new Cpf(dto.getCpf());
    }
    public static Email convert(EmailDTO dto){
        return new Email(dto.getEmail());
    }
    public static Telefone convert(TelefoneDTO dto){
        return new Telefone(dto.getTelefone());
    }
    public static Endereco convert(EnderecoDTO dto){
        Endereco endereco = new Endereco();
        endereco.setBairro(dto.getBairro());
        endereco.setRua(dto.getRua());
        endereco.setCep(dto.getCep());
        endereco.setCidade(dto.getCidade());
        endereco.setComplemento(dto.getComplemento());
        endereco.setEstado(dto.getEstado());
        endereco.setNumero(dto.getNumero());
        return endereco;
    }
    public static CpfDTO convert(Cpf cpf){
        return new CpfDTO(cpf.getCpf());
    }
    public static EmailDTO convert(Email email){
        return new EmailDTO(email.getEmail());
    }
    public static TelefoneDTO convert(Telefone telefone){
        return new TelefoneDTO(telefone.getTelefone());
    }
    public static EnderecoDTO convert(Endereco endereco){
        EnderecoDTO dto = new EnderecoDTO();
        dto.setBairro(endereco.getBairro());
        dto.setRua(endereco.getRua());
        dto.setCep(endereco.getCep());
        dto.setCidade(endereco.getCidade());
        dto.setComplemento(endereco.getComplemento());
        dto.setEstado(endereco.getEstado());
        dto.setNumero(endereco.getNumero());
        return dto;
    }
}
