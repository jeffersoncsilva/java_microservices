package jefferson.livro.javabackend.productapi.model;

import jakarta.persistence.*;
import jefferson.livro.javabackend.productapi.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private Float preco;
    private String descricao;
    private String productIdentifier;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public static Product convert(ProductDTO dto){
        var p = new Product();
        p.setNome(dto.getNome());
        p.setPreco(dto.getPreco());
        p.setDescricao(dto.getDescricao());
        p.setProductIdentifier(dto.getProductIdentifier());
        if(dto.getCategoryDTO() != null){
            p.setCategory(Category.convert(dto.getCategoryDTO()));
        }
        return p;
    }

}
