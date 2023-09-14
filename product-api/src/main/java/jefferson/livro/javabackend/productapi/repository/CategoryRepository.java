package jefferson.livro.javabackend.productapi.repository;

import jefferson.livro.javabackend.productapi.model.Category;
import jefferson.livro.javabackend.productapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
