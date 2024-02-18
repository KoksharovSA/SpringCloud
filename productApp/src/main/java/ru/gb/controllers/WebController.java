package ru.gb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gb.models.Product;
import ru.gb.models.Purchase;
import ru.gb.services.ProductService;
import ru.gb.services.PurchaseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebController {
    private final ProductService productService;
    private final PurchaseService purchaseService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Void> addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.ok().body(null);
    }
    @PostMapping("/deleteProductById/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id){
        productService.deleteProductById(id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/getAllPurchase")
    public ResponseEntity<List<Purchase>> getAllPurchase(){
        return ResponseEntity.ok().body(purchaseService.getAllPurchase());
    }

    @GetMapping("{user}/getAllPurchasesUser")
    public ResponseEntity<List<Purchase>> getAllPurchasesUser(@PathVariable("user") String user){
        return ResponseEntity.ok().body(purchaseService.getAllPurchasesUser(user));
    }

    @PostMapping("/addPurchase")
    public ResponseEntity<Void> addPurchase(@RequestBody Purchase purchase){
        purchaseService.addPurchase(purchase);
        return ResponseEntity.ok().body(null);
    }
    @PostMapping("{id}/deletePurchaseById")
    public ResponseEntity<Void> deletePurchaseById(@PathVariable("id") Long id){
        purchaseService.deletePurchaseById(id);
        return ResponseEntity.ok().body(null);
    }

}
