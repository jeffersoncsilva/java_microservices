package jeff.livro.backend.shopapi.models;
import jakarta.persistence.Embeddable;
import jeff.livro.backend.shopapi.dtos.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Item {
    private String productIdentifier;
    private Float price;

    public static Item convert(ItemDTO dto){
        Item item = new Item();
        item.setProductIdentifier(dto.getProductIdentifier());
        item.setPrice(dto.getPrice());
        return item;
    }
}
