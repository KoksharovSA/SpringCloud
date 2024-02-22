package ru.gb.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.models.Product;
import ru.gb.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getProductLowerPrice(double price) {
        return productRepository.findAll().stream().filter(x -> x.getPrice() <= price).toList();
    }

    public List<Product> getProductUpperPrice(double price) {
        return productRepository.findAll().stream().filter(x -> x.getPrice() >= price).toList();
    }

    public List<Product> getProductIfNameContains(String text) {
        return productRepository.findAll().stream().filter(x->x.getName().contains(text)).toList();
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
