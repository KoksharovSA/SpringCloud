package ru.gb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.models.Product;

/**
 * Класс JPA репозитория для общения с базой данных
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
