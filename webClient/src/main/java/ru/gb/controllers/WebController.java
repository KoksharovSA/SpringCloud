package ru.gb.controllers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.models.Product;
import ru.gb.models.Purchase;
import ru.gb.services.FileGateway;
import ru.gb.services.WebClientService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final FileGateway fileGateway;
    private final WebClientService webClientService;
    private final Counter requestCounter = Metrics.counter("request_count");

    @GetMapping("/adminPanel")
    public String adminPanel(Model model){
        fileGateway.writeToFile("Product.txt", LocalDateTime.now().toString());
        fileGateway.writeToFile("Product.txt", "/adminPanel");
        fileGateway.writeToFile("Product.txt", "___________________");
        requestCounter.increment();
        List<Product> products = webClientService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "adminPanel";
    }

    @GetMapping("/personalAccount")
    public String personalAccount(Model model){
        fileGateway.writeToFile("Product.txt", LocalDateTime.now().toString());
        fileGateway.writeToFile("Product.txt", "/personalAccount");
        fileGateway.writeToFile("Product.txt", "___________________");
        requestCounter.increment();
        List<Purchase> purchases = webClientService.getAllPurchase();
        model.addAttribute("purchases", purchases);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "personalAccount";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model){
        fileGateway.writeToFile("Product.txt", LocalDateTime.now().toString());
        fileGateway.writeToFile("Product.txt", "/loginPage");
        fileGateway.writeToFile("Product.txt", "___________________");
        requestCounter.increment();
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "loginPage";
    }

    public String index(Model model){
        requestCounter.increment();
        List<Product> products = webClientService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }

    @GetMapping("/index")
    public String mainPageIndex(Model model){
        fileGateway.writeToFile("Product.txt", LocalDateTime.now().toString());
        fileGateway.writeToFile("Product.txt", "/index");
        fileGateway.writeToFile("Product.txt", "___________________");
        requestCounter.increment();
        return index(model);
    }

    @GetMapping("/")
    public String mainPage(Model model){
        fileGateway.writeToFile("Product.txt", LocalDateTime.now().toString());
        fileGateway.writeToFile("Product.txt", "/");
        fileGateway.writeToFile("Product.txt", "___________________");
        requestCounter.increment();
        return index(model);
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product, Model model){
        fileGateway.writeToFile("Product.txt", LocalDateTime.now().toString());
        fileGateway.writeToFile("Product.txt", "/addProduct");
        fileGateway.writeToFile("Product.txt", product.toString());
        fileGateway.writeToFile("Product.txt", "___________________");
        requestCounter.increment();
        webClientService.addProduct(product);
        List<Product> products = webClientService.getAllProducts();
        model.addAttribute("products", products);
        return "adminPanel";
    }

    @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable("id") Long id){
        requestCounter.increment();
        Purchase purchase = new Purchase();
        purchase.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        Product product = webClientService.getProductById(id);
        purchase.setProduct(product);
        webClientService.addPurchase(purchase);
        fileGateway.writeToFile("Product.txt", LocalDateTime.now().toString());
        fileGateway.writeToFile("Product.txt", "/buy/"+ id);
        fileGateway.writeToFile("Product.txt", product.toString());
        fileGateway.writeToFile("Product.txt", "___________________");
        return "redirect:/index";
    }

    @PostMapping("/deletePurchase/{id}")
    public String deletePurchase(@PathVariable("id") Long id){
        requestCounter.increment();
        webClientService.deletePurchaseById(id);
        fileGateway.writeToFile("Product.txt", LocalDateTime.now().toString());
        fileGateway.writeToFile("Product.txt", "/deletePurchase/"+ id);
        fileGateway.writeToFile("Product.txt", "___________________");
        return "redirect:/personalAccount";
    }
    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        requestCounter.increment();
        webClientService.deleteProductById(id);
        fileGateway.writeToFile("Product.txt", LocalDateTime.now().toString());
        fileGateway.writeToFile("Product.txt", "/deleteProduct/"+ id);
        fileGateway.writeToFile("Product.txt", "___________________");
        return "redirect:/adminPanel";
    }
}
