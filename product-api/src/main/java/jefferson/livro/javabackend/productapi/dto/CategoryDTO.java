package jefferson.livro.javabackend.productapi.dto;

import jakarta.validation.constraints.NotNull;
import jefferson.livro.javabackend.productapi.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @NotNull
    private Long id;
    private String nome;

    public static CategoryDTO convert(Category c){
        var dto = new CategoryDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        return dto;
    }
}
