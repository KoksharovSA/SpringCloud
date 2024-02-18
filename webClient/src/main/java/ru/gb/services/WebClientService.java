package ru.gb.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.gb.clientsApi.ProductClientApi;
import ru.gb.models.Product;
import ru.gb.models.Purchase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebClientService {
    private final ProductClientApi productClientApi;

    public List<Product> getAllProducts() {
        return productClientApi.getAllProducts().getBody();
    }

    public Product getProductById(Long id) {
        return productClientApi.getProductById(id).getBody();
    }

    public List<Product> getProductLowerPrice(double price) {
        return getAllProducts().stream().filter(x -> x.getPrice() <= price).toList();
    }

    public List<Product> getProductUpperPrice(double price) {
        return getAllProducts().stream().filter(x -> x.getPrice() >= price).toList();
    }

    public List<Product> getProductIfNameContains(String text) {
        return getAllProducts().stream().filter(x->x.getName().contains(text)).toList();
    }

    public void addProduct(Product product){
        productClientApi.addProduct(product);
    }
    public void deleteProductById(Long id){
        productClientApi.deleteProductById(id);
    }


    public List<Purchase> getAllPurchase() {
        return productClientApi.getAllPurchase().getBody();
    }

    public List<Purchase> getAllPurchasesUser(String user) {
        return productClientApi.getAllPurchasesUser(user).getBody();
    }

    public void addPurchase(Purchase purchase){
        productClientApi.addPurchase(purchase);
    }

    public void deletePurchaseById(Long id){
        productClientApi.deletePurchaseById(id);
    }

}
