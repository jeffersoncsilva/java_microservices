package jefferson.livro.javabackend.productapi.service;

import dtos.ProductDTO;
import jefferson.livro.javabackend.productapi.dtoconverters.DTOConverter;
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
    private final ProductRepository repository;

    public List<ProductDTO> getAll(){
        var products = repository.findAll();
        return products.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId){
        var p = repository.getProductByCategory(categoryId);
        return p.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String pi){
        var p = repository.findByProductIdentifier(pi);
        if(p != null)
            return DTOConverter.convert(p);
        return null;
    }

    public ProductDTO save(ProductDTO p){
        var product = repository.save(DTOConverter.convert(p));
        return DTOConverter.convert(product);
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

        return DTOConverter.convert(repository.save(p));
    }

    public Page<ProductDTO> getAllPage(Pageable page){
        Page<Product> users = repository.findAll(page);
        return users.map(DTOConverter::convert);
    }
}
