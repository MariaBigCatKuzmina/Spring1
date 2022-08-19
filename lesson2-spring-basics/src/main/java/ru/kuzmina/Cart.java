package ru.kuzmina;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@Scope("prototype")
public class Cart {
    private final ProductRepository productRepository;
    private List<Product> products;

    @PostConstruct
    private void init(){
        products = new ArrayList<>();
    }

    public Cart(ProductRepository products) {
        this.productRepository = products;
    }

    public void addProductById(long id) {
        Optional<Product> product = productRepository.getById(id);
        product.ifPresent(value -> products.add(value));
    }

    public void dropProductById(long id) {
        Optional<Product> product = productRepository.getById(id);
        product.ifPresent(value -> products.remove(value));
    }

    public void clear() {
        products.clear();
    }

    public List<Product> getAll() {
        return products;
    }

    public int size() {
        return products.size();
    }
}
