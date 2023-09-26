package jefferson.livro.javabackend.model;

import jakarta.persistence.*;
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
    private String password;
    private Cpf cpf;
    @OneToOne
    @JoinColumn(name="endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    private Email email;
    private Telefone telefone;
    private LocalDateTime dataCadastro;

    @Override
    public int hashCode() {
        return (cpf.getCpf() + nome + email.getEmail() + telefone.getTelefone()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User user = (User) obj;
            return user.getCpf().equals(this.getCpf()) &&
                    user.getNome().equals(this.getNome()) &&
                    user.getEmail().equals(this.getEmail()) &&
                    user.getTelefone().equals(this.getTelefone());
        }
        return false;
    }
}
