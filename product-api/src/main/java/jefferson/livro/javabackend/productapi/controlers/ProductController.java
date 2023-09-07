package jefferson.livro.javabackend.productapi.controlers;

import jakarta.validation.Valid;
import jefferson.livro.javabackend.productapi.dto.ProductDTO;
import jefferson.livro.javabackend.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private ProductService service;
    @GetMapping
    public List<ProductDTO> getProducts(){
        return service.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductByCategory(@PathVariable Long categoryId){
        return service.getProductByCategoryId(categoryId);
    }

    @GetMapping("/{productIdentifier}")
    public ProductDTO findById(@PathVariable String pi){
        return service.findByProductIdentifier(pi);
    }

    @PostMapping
    public ProductDTO newProduct(@Valid @RequestBody ProductDTO dto){
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PostMapping("/{id}")
    public ProductDTO editProduct(@PathVariable Long id, @RequestBody ProductDTO dto){
        return service.editProduct(id, dto);
    }

    @GetMapping("/pageable")
    public Page<ProductDTO> getProductsPage(Pageable page){
        return service.getAllPage(page);
    }

}
