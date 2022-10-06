package ru.kuzmina.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kuzmina.repositories.OrderRepository;


@Controller
@RequestMapping("/info")
public class InfoController {
    private final OrderRepository orderRepository;

    public InfoController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/userproducts/{id}")
    public String getAllBoughtProductsForUser(@PathVariable(name = "id") Long userId, Model model) {
        model.addAttribute("orders",orderRepository.allBoughtProductsForUser(userId, "o.product_id"));
        return "order.html";
    }
}
