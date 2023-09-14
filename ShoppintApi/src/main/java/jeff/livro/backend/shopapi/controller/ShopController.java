package jeff.livro.backend.shopapi.controller;


import dtos.ShopDTO;
import jakarta.validation.Valid;
import jeff.livro.backend.shopapi.dtos.ShopReportDTO;
import jeff.livro.backend.shopapi.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ShopDTO newShop(@RequestHeader(name="key", required = true) String key, @Valid @RequestBody ShopDTO dto){
        return service.save(dto, key);
    }

    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate dataInicio,
                                          @RequestParam(name = "dataFim", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate dataFim,
                                          @RequestParam(name = "valorMinimo", required = false) Float valorMinimo){
        return service.getShopsByFilter(dataInicio, dataFim, valorMinimo);
    }

    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate dataInicio,
                                         @RequestParam(name = "dataFim", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate dataFim){
        return service.getReportByDate(dataInicio, dataFim);
    }
}
