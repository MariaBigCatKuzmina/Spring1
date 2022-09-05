package ru.kuzmina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kuzmina.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByTitleLike(String titleFilter);
}
