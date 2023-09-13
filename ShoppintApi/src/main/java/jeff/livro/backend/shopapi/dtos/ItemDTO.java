package jeff.livro.backend.shopapi.dtos;

import jeff.livro.backend.shopapi.models.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float price;

    public static ItemDTO convert(Item item){
        ItemDTO dto = new ItemDTO();
        dto.setProductIdentifier(item.getProductIdentifier());
        dto.setPrice(item.getPrice());
        return dto;
    }
}
