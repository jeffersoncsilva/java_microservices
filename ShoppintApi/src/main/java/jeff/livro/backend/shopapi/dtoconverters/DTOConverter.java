package jeff.livro.backend.shopapi.dtoconverters;

import dtos.ItemDTO;
import dtos.ShopDTO;
import jeff.livro.backend.shopapi.models.Item;
import jeff.livro.backend.shopapi.models.Shop;

import java.util.stream.Collectors;

public class DTOConverter {
    public static Item convert(ItemDTO dto){
        Item item = new Item();
        item.setProductIdentifier(dto.getProductIdentifier());
        item.setPrice(dto.getPrice());
        return item;
    }

    public static ItemDTO convert(Item dto){
        ItemDTO item = new ItemDTO();
        item.setProductIdentifier(dto.getProductIdentifier());
        item.setPrice(dto.getPrice());
        return item;
    }

    public static Shop convert(ShopDTO dto){
        Shop sop = new Shop();
        sop.setUserIdentifier(dto.getUserIdentifier());
        sop.setTotal(dto.getTotal());
        sop.setDate(dto.getDate());
        sop.setItems(dto.getItems().stream().map(DTOConverter::convert).collect(Collectors.toList()));
        return sop;
    }

    public static ShopDTO convert(Shop dto){
        ShopDTO sop = new ShopDTO();
        sop.setUserIdentifier(dto.getUserIdentifier());
        sop.setTotal(dto.getTotal());
        sop.setDate(dto.getDate());
        sop.setItems(dto.getItems().stream().map(DTOConverter::convert).collect(Collectors.toList()));
        return sop;
    }
}
