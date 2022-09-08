package ru.kuzmina.controllers;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.model.Product;
import ru.kuzmina.model.QProduct;
import ru.kuzmina.repositories.ProductRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public String productList(@RequestParam(required = false) String productTitleFilter,
                              @RequestParam(required = false) Integer priceFilterMin,
                              @RequestParam(required = false) Integer priceFilterMax,
                              Model model) {
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
        model.addAttribute("products", productRepository.findAll(predicate));
        return "product";
    }

    @GetMapping("/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).get());
        return "product_form";
    }

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product("", 0.0);
        model.addAttribute("product", product);
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String dropProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

}
