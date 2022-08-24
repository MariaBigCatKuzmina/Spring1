package ru.kuzmina.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private Map<Long,Product> products;
    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    private void init() {
        products = new ConcurrentHashMap<>();
        add(new Product( "Milk", 180.0));
        add(new Product("Bread", 80.0));
        add(new Product("Water", 50.0));
        add(new Product("Eggs", 200.0));
        add(new Product("Carrots", 140.30));
    }

    public void add(Product product){
        Long id = identity.incrementAndGet();
        product.setId(id);
        products.put(id, product);
    }
    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> getById(long id) {
        return products.values().
                stream().
                filter(product -> product.getId() == id).
                findFirst();
//        return Optional.ofNullable(products.values().
//                stream().
//                filter(product -> product.getId() == id).
//                findFirst().
//                orElseThrow(IndexOutOfBoundsException::new));
    }

    public void update(Product product){
        products.put(product.getId(), product);
    }

}
