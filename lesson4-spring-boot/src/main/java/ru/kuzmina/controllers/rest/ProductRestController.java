package ru.kuzmina.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.exceptions.ResourceNotFoundException;
import ru.kuzmina.model.Dto.ProductDto;
import ru.kuzmina.services.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductRestController {
    ProductService productService;

    @GetMapping
    public Page<ProductDto> productList(@RequestParam(required = false) String productTitleFilter,
                                        @RequestParam(required = false) Integer priceFilterMin,
                                        @RequestParam(required = false) Integer priceFilterMax,
                                        @RequestParam(required = false) Optional<Integer> page,
                                        @RequestParam(required = false) Optional<Integer> size,
                                        @RequestParam(required = false) Optional<String> sortField,
                                        Model model) {
        int curPage = page.orElse(1) - 1;
        int curSize = size.orElse(3);
        String sortFieldName = sortField.orElse("id");

        sortFieldName = sortFieldName.isBlank() ? "id" : sortFieldName;

        return productService.findAll(productTitleFilter, priceFilterMin,
                priceFilterMax, curPage, curSize, sortFieldName);
    }


    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id, Model model) {
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

}
