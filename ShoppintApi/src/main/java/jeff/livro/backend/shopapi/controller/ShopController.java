package jeff.livro.backend.shopapi.controller;


import jakarta.validation.Valid;
import jeff.livro.backend.shopapi.dtos.ShopDTO;
import jeff.livro.backend.shopapi.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService service;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops(){
        return service.getAll();
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable String userIdentifier){
        return service.getByUser(userIdentifier);
    }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO dto){
        return service.getByDate(dto);
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping("/shopping")
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO newShop(@Valid @RequestBody ShopDTO dto){
        return service.save(dto);
    }
}
