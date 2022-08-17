package ru.kuzmina;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kuzmina.config.ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Cart cart1 = context.getBean(Cart.class);
        cart1.addProductById(1L);
        cart1.addProductById(2L);
        cart1.addProductById(4L);

        Cart cart2 = context.getBean(Cart.class);
        cart2.addProductById(2L);
        cart2.addProductById(5L);
        cart2.addProductById(6L);

        System.out.println("Cart1: " + cart1);
        System.out.println("Cart2: " + cart2);

    }
}
