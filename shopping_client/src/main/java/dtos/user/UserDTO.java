package dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "Nome é obrigatório.")
    private String nome;
    @NotNull(message = "CPF é obrigatório.")
    private CpfDTO cpf;
    @NotNull(message = "Senha é obrigatório.")
    private String senha;
    private EnderecoDTO endereco;
    @NotNull(message = "E-mail e obrigatório.")
    private EmailDTO email;
    private TelefoneDTO telefone;
    private LocalDateTime dataCadastro;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(nome, userDTO.nome) && Objects.equals(cpf, userDTO.cpf) && Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, email);
    }
}
