package ru.gb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.models.Purchase;

/**
 * Класс JPA репозитория для общения с базой данных
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
