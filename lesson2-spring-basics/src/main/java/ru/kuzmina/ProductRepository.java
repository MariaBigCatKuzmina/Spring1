package ru.kuzmina;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    private void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Milk", 180));
        products.add(new Product(2L, "Bread", 80));
        products.add(new Product(3L, "Water", 50));
        products.add(new Product(4L, "Eggs", 200));
        products.add(new Product(5L, "Carrots", 140));
    }

    public List<Product> getAll() {
        return products;
    }

    public Optional<Product> getById(long id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }

    @Override
    public String toString() {
        return "ProductRepository{" +
                "products=" + products +
                '}';
    }
}
