package ru.gb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.models.Product;
import ru.gb.models.Purchase;
import ru.gb.services.WebClientService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final WebClientService webClientService;

    @GetMapping("/adminPanel")
    public String adminPanel(Model model){
        List<Product> products = webClientService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "adminPanel";
    }

    @GetMapping("/personalAccount")
    public String personalAccount(Model model){
        List<Purchase> purchases = webClientService.getAllPurchasesUser(SecurityContextHolder.getContext().getAuthentication().getName()).getBody();
        model.addAttribute("purchases", purchases);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "personalAccount";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model){
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "loginPage";
    }

    public String index(Model model){
        List<Product> products = webClientService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }

    @GetMapping("/index")
    public String mainPageIndex(Model model){
        return index(model);
    }

    @GetMapping("/")
    public String mainPage(Model model){
        return index(model);
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product, Model model){
        webClientService.addProduct(product);
        List<Product> products = webClientService.getAllProducts();
        model.addAttribute("products", products);
        return "adminPanel";
    }

    @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable("id") Long id){
        Purchase purchase = new Purchase();
        purchase.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        purchase.setProduct(webClientService.getProductById(id));
        webClientService.addPurchase(purchase);
        return "redirect:/index";
    }

    @PostMapping("/deletePurchase/{id}")
    public String deletePurchase(@PathVariable("id") Long id){
        webClientService.deletePurchaseById(id);
        return "redirect:/personalAccount";
    }
    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        webClientService.deleteProductById(id);
        return "redirect:/adminPanel";
    }
}
