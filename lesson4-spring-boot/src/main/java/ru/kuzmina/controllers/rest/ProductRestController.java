package ru.kuzmina.controllers.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.exceptions.ResourceNotFoundException;
import ru.kuzmina.model.Dto.ProductDto;
import ru.kuzmina.services.ProductService;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductRestController {
    ProductService productService;

    @GetMapping
    public Page<ProductDto> productList(@RequestParam(required = false) String productTitleFilter,
                                        @RequestParam(required = false) Integer priceFilterMin,
                                        @RequestParam(required = false) Integer priceFilterMax,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(required = false, defaultValue = "5") int size,
                                        @RequestParam(defaultValue = "id") String sortField) {

        String sortFieldName = sortField.isBlank() ? "id" : sortField;

        return productService.findAll(productTitleFilter, priceFilterMin,
                priceFilterMax, page, size, sortFieldName);
    }

    @GetMapping("/{id}/get")
    public ProductDto getProduct(@PathVariable("id") Long id) {
        log.info("get product " + id);
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id:" + id));
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void dropProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }
}
