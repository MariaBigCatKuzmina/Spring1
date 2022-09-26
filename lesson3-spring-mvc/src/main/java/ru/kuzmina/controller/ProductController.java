package ru.kuzmina.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuzmina.persist.Product;
import ru.kuzmina.persist.ProductRepository;

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
    public String updateProduct(@RequestParam Long id, Model model){
        model.addAttribute("product", productRepository.getById(id));
        return "product_form";
    }

    @PostMapping
    public String saveProduct(Product product) {
        productRepository.update(product);
        return "redirect:product";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product("новый товар", 0.0);
        productRepository.add(product);
        model.addAttribute("product", product);
        return "product_form";
    }

}
