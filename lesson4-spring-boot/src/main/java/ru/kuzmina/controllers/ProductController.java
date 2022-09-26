package ru.kuzmina.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.exceptions.ApplicationError;
import ru.kuzmina.model.Dto.ProductDto;
import ru.kuzmina.services.ProductService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String productList(@RequestParam(required = false) String productTitleFilter,
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

        model.addAttribute("products", productService.findAll(productTitleFilter, priceFilterMin,
                priceFilterMax, curPage, curSize, sortFieldName));
        return "product";
    }

    @GetMapping("/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        Optional<ProductDto> product = productService.findById(id);
//        ProductDto product = productService.findById(id).orElseThrow(() -> new ServiceNotFoundException("Product not found, id:" + id));
        if (product.isPresent()) {
            model.addAttribute("product", product);
            return "product_form";
        }
        model.addAttribute("error", new ApplicationError(404, "Product not found"));
        return "error";
    }

    @PostMapping
    public String saveProduct(@Valid ProductDto product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        ProductDto product = new ProductDto("", 0.0);
        model.addAttribute("product", product);
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String dropProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/product";
    }

}
