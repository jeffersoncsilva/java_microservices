package jefferson.livro.javabackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jefferson.livro.javabackend.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;

    public static User convert(UserDTO dto){
        User user = new User();
        user.setNome(dto.getNome());
        user.setEndereco(dto.getEndereco());
        user.setCpf(dto.getCpf());
        user.setEmail(dto.getEmail());
        user.setTelefone(dto.getTelefone());
        user.setDataCadastro(dto.getDataCadastro());
        return user;
    }
}
