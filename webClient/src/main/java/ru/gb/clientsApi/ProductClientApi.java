package ru.gb.clientsApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.models.Product;
import ru.gb.models.Purchase;

import java.util.List;


@FeignClient(name = "productApp")
public interface ProductClientApi {

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts();

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id);

    @PostMapping("/addProduct")
    public ResponseEntity<Void> addProduct(@RequestBody Product product);
    @PostMapping("/deleteProductById/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id);



    @GetMapping("/getAllPurchase")
    public ResponseEntity<List<Purchase>> getAllPurchase();

    @GetMapping("/{user}/getAllPurchasesUser")
    public ResponseEntity<List<Purchase>> getAllPurchasesUser(@PathVariable("user") String user);

    @PostMapping("/addPurchase")
    public ResponseEntity<Void> addPurchase(@RequestBody Purchase purchase);
    @PostMapping("/{id}/deletePurchaseById")
    public ResponseEntity<Void> deletePurchaseById(@PathVariable("id") Long id);
}
