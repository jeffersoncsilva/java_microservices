package jefferson.livro.javabackend.productapi.dtoconverters;

import dtos.CategoryDTO;
import dtos.ProductDTO;
import jefferson.livro.javabackend.productapi.model.Category;
import jefferson.livro.javabackend.productapi.model.Product;

public class DTOConverter {
    public static CategoryDTO convert(Category c){
        var dto = new CategoryDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        return dto;
    }

    public static Category convert(CategoryDTO dto){
        var c = new Category();
        c.setNome(dto.getNome());
        c.setId(dto.getId());
        return c;
    }

    public static ProductDTO convert(Product p){
        var dto = new ProductDTO();
        dto.setCategoryDTO(convert(p.getCategory()));
        dto.setNome(p.getNome());
        dto.setDescricao(p.getDescricao());
        dto.setPreco(p.getPreco());
        dto.setProductIdentifier(p.getProductIdentifier());
        return dto;
    }

    public static Product convert(ProductDTO dto){
        var p = new Product();
        p.setNome(dto.getNome());
        p.setDescricao(dto.getDescricao());
        p.setPreco(dto.getPreco());
        p.setProductIdentifier(dto.getProductIdentifier());
        p.setCategory(convert(dto.getCategoryDTO()));
        return p;
    }

}
