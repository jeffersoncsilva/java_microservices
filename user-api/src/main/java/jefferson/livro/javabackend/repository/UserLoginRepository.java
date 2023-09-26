package jefferson.livro.javabackend.repository;

import jefferson.livro.javabackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserLoginRepository extends JpaRepository<User, Long> {

    User findByCpfAndPassword(String cpf, String password);

    User findByEmailAndPassword(String email, String password);
}
