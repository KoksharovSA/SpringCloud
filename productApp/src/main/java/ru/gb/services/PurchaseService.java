package ru.gb.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.models.Purchase;
import ru.gb.repositories.PurchaseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchase() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> getAllPurchasesUser(String user) {
        return purchaseRepository.findAll().stream().filter(x->x.getUserName().equals(user)).toList();
    }

    public void addPurchase(Purchase purchase){
        purchaseRepository.save(purchase);
    }

    public void deletePurchaseById(Long id){
        purchaseRepository.deleteById(id);
    }
}
