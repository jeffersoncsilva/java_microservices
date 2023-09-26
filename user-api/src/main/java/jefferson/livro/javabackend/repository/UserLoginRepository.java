package jefferson.livro.javabackend.repository;

import jefferson.livro.javabackend.model.Cpf;
import jefferson.livro.javabackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<User, Long> {

    User findByCpfAndSenha(Cpf cpf, String password);

    User findByEmailAndSenha(String email, String password);
}
