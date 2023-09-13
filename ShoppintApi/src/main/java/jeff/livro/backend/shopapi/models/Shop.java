package jeff.livro.backend.shopapi.models;

import jakarta.persistence.*;
import jeff.livro.backend.shopapi.dtos.ShopDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="shop")
public class Shop {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    private String userIdentifier;
    private float total;
    private LocalDateTime date;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    private List<Item> items;

    public static Shop convert(ShopDTO dto){
        Shop sop = new Shop();
        sop.setUserIdentifier(dto.getUserIdentifier());
        sop.setTotal(dto.getTotal());
        sop.setDate(dto.getDate());
        sop.setItems(dto.getItems().stream().map(Item::convert).collect(Collectors.toList()));
        return sop;
    }
}
