package ru.kuzmina;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public int getProductQuantity() {
       return productRepository.getAll().size();
    }

    public List<Product> getAllProducts () {
        return productRepository.getAll();
    }
}
