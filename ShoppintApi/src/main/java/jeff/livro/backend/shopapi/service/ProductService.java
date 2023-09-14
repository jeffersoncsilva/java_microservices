package jeff.livro.backend.shopapi.service;

import dtos.ProductDTO;
import exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private String productApiURL = "http://localhost:8081";
    public ProductDTO getProductByIdentifier(String productIdentifier){
        try{
            WebClient webClient = WebClient.builder().baseUrl(productApiURL).build();
            Mono<ProductDTO> prodct = webClient.get().uri("/product/"+productIdentifier).retrieve().bodyToMono(ProductDTO.class);
            return prodct.block();
        }catch (Exception e){
            throw new ProductNotFoundException();
        }
    }
}
