package ru.gb.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.models.Product;
import ru.gb.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


@SpringBootTest
public class ProductServiceIntegrationTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void getAllProductsIdTest(){
        Product product1 = new Product();
        product1.setId(333L);
        product1.setName("Phone1");
        product1.setDescription("SmartPhone");
        product1.setPictureUrl("");
        product1.setPrice(20000);

        Product product2 = new Product();
        product2.setId(444L);
        product2.setName("Phone1");
        product2.setDescription("SmartPhone");
        product2.setPictureUrl("");
        product2.setPrice(40000);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        given(productRepository.findAll()).willReturn(products);
        assertEquals(productService.getAllProducts(), products);
    }

    @Test
    public void getProductByIdTest(){

        Product product1 = new Product();
        product1.setId(333L);
        product1.setName("Phone1");
        product1.setDescription("SmartPhone");
        product1.setPictureUrl("");
        product1.setPrice(20000);

        given(productRepository.findById(product1.getId())).willReturn(Optional.of(product1));
        assertEquals(productService.getProductById(product1.getId()), product1);
    }
}
