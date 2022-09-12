package ru.kuzmina.services;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kuzmina.model.Dto.ProductDto;
import ru.kuzmina.model.Product;
import ru.kuzmina.model.QProduct;
import ru.kuzmina.model.mapper.ProductDtoMapper;
import ru.kuzmina.repositories.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoMapper mapper;

    public ProductService(ProductRepository productRepository, ProductDtoMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public Page<Product> findAll(String productTitleFilter,
                                 Integer priceFilterMin,
                                 Integer priceFilterMax,
                                 int page, int size, String sortField) {

        BooleanBuilder predicate = new BooleanBuilder();

        QProduct product = QProduct.product;


        if (productTitleFilter != null && !productTitleFilter.isBlank()) {
            predicate.and(product.title.contains(productTitleFilter.trim()));
        }
        if (priceFilterMin != null && priceFilterMin > 0) {
            predicate.and(product.price.goe(priceFilterMin));
        }
        if (priceFilterMax != null && priceFilterMax > 0) {
            predicate.and(product.price.loe(priceFilterMax));
        }

        return productRepository.findAll(predicate, PageRequest.of(page, size, Sort.by(sortField)));
    }

    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }

    public ProductDto save(ProductDto product) {
        return mapper.map(productRepository.save(mapper.map(product)));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
