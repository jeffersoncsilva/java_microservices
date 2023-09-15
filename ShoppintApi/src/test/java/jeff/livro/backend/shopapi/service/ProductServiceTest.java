package jeff.livro.backend.shopapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.ProductDTO;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    public static MockWebServer mockBackEnt;
    @InjectMocks
    private ProductService productService;
    @BeforeEach
    void setup() throws IOException{
        mockBackEnt = new MockWebServer();
        mockBackEnt.start();
        String baseUrl = String.format("http://localhost:%s", mockBackEnt.getPort());
        ReflectionTestUtils.setField(productService, "productApiURL", baseUrl);
    }

    @AfterEach
    void tearDown() throws IOException{
        mockBackEnt.shutdown();
    }

    @Test
    void test_getProductByIdentifier() throws Exception{
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPreco(1000F);
        productDTO.setProductIdentifier("prod-identifier");

        ObjectMapper objectMapper = new ObjectMapper();

        mockBackEnt.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(productDTO)).addHeader("Content-Type", "application/json"));

        productDTO = productService.getProductByIdentifier("prod-identifier");

        Assertions.assertEquals(1000F, productDTO.getPreco());
        Assertions.assertEquals("prod-identifier", productDTO.getProductIdentifier());
    }
}