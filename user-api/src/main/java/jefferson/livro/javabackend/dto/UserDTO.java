package jefferson.livro.javabackend.dto;

import jakarta.validation.constraints.NotBlank;
import jefferson.livro.javabackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Nome é obrigatório.")
    private String nome;
    @NotBlank(message = "CPF é obrigatório.")
    private String cpf;
    private String endereco;
    @NotBlank(message = "E-mail e obrigatório.")
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;

    public static UserDTO convert(User user){
        UserDTO dto = new UserDTO();
        dto.setNome(user.getNome());
        dto.setEndereco(user.getEndereco());
        dto.setEmail(user.getEmail());
        dto.setCpf(user.getCpf());
        dto.setTelefone(user.getTelefone());
        dto.setDataCadastro(user.getDataCadastro());
        return dto;
    }
}
