package jefferson.livro.javabackend.productapi.service;

import jefferson.livro.javabackend.productapi.dto.ProductDTO;
import jefferson.livro.javabackend.productapi.model.Product;
import jefferson.livro.javabackend.productapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository repository;

    public List<ProductDTO> getAll(){
        var products = repository.findAll();
        return products.stream().map(ProductDTO::convert).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId){
        var p = repository.getProductByCategory(categoryId);
        return p.stream().map(ProductDTO::convert).collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String pi){
        var p = repository.findByProductIdentifier(pi);
        if(p != null)
            return ProductDTO.convert(p);
        return null;
    }

    public ProductDTO save(ProductDTO p){
        var product = repository.save(Product.convert(p));
        return ProductDTO.convert(product);
    }

    public void delete(long id){
        Optional<Product> p = repository.findById(id);
        if(p.isPresent())
            repository.delete(p.get());
    }

    public ProductDTO editProduct(long id, ProductDTO dto){
        var p = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        if(p.getNome() != null || !dto.getNome().isEmpty())
            p.setNome(dto.getNome());
        if(dto.getPreco() != null)
            p.setPreco(dto.getPreco());

        return ProductDTO.convert(repository.save(p));
    }

    public Page<ProductDTO> getAllPage(Pageable page){
        Page<Product> users = repository.findAll(page);
        return users.map(ProductDTO::convert);
    }
}
