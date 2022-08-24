package ru.kuzmina.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.persist.Product;
import ru.kuzmina.persist.ProductRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public String productList(Model model) {
        model.addAttribute("products", productRepository.getAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productRepository.getById(id));
        return "product_form";
    }

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.update(product);
        return "redirect:product";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product("", 0.0);
        productRepository.add(product);
        model.addAttribute("product", product);
        return "product_form";
    }

}
