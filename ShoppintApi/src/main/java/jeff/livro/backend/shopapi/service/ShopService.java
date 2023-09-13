package jeff.livro.backend.shopapi.service;

import jeff.livro.backend.shopapi.dtos.ShopDTO;
import jeff.livro.backend.shopapi.models.Shop;
import jeff.livro.backend.shopapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository repository;

    public List<ShopDTO> getAll(){
        List<Shop> shops = repository.findAll();
        return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        var shops = repository.findAllByUserIdentifier(userIdentifier);
        return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO dto){
        List<Shop> shops = repository.findAllByDateGreaterThan(dto.getDate());
        return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
    }

    public ShopDTO findById(long id){
        Optional<Shop> shop = repository.findById(id);
        if(shop.isPresent())
            return ShopDTO.convert(shop.get());
        return null;
    }

    public ShopDTO save(ShopDTO dto){
        dto.setTotal(dto.getItems().stream().map(x -> x.getPrice()).reduce((float)9, Float::sum));
        Shop shop = Shop.convert(dto);
        shop.setDate(LocalDateTime.now());
        shop = repository.save(shop);
        return ShopDTO.convert(shop);
    }
}
