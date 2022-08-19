package ru.kuzmina;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kuzmina.config.ApplicationConfiguration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        String commands = "Команды: " +
                System.lineSeparator() +
                "new - создать новую корзину" +
                System.lineSeparator() +
                "print - показать все товары в корзине" +
                System.lineSeparator() +
                "add - добавить товар в корзину по id" +
                System.lineSeparator() +
                "del - удалить товар из корзины по id" +
                System.lineSeparator() +
                "clear - удалить все товары из корзины по id" +
                System.lineSeparator() +
                "close - закрыть приложение" +
                System.lineSeparator() +
                "products - показать доступные товары";

        System.out.println(commands);
        Scanner scanner = new Scanner(System.in);
        Cart cart = null;
        while (true) {
            System.out.println("Введите команду:");
            String command = scanner.nextLine().toLowerCase().trim();
            switch (command) {
                case "new" -> {
                    cart = context.getBean(Cart.class);
                }
                case "print" -> {
                    if (cart == null || cart.size() == 0) {
                        System.out.println("Корзина пуста");
                    } else {
                        cart.getAll().forEach(System.out::println);
                    }
                }
                case "add" -> {
                    if (cart == null) {
                        cart = context.getBean(Cart.class);
                    }
                    System.out.println("Введите id товара:");
                    long id = scanner.nextLong();
                    cart.addProductById(id);
                }
                case "del" -> {
                    if (cart == null || cart.size() == 0) {
                        System.out.println("Корзина пуста");
                    } else {
                        System.out.println("Введите id товара:");
                        long id = scanner.nextLong();
                        cart.dropProductById(id);
                    }
                }
                case "clear" -> {
                    if (cart == null || cart.size() == 0) {
                        System.out.println("Корзина пуста");
                    } else {
                        cart.clear();
                    }
                }
                case "close" -> {
                    return;
                }
                case "products" -> {
                    ProductService productService = context.getBean(ProductService.class);
                    productService.getAllProducts().forEach(System.out::println);
                }
                case "help" -> {
                    System.out.println(commands);
                }
                default -> {
                    System.out.println("Неизвестная команда, введите help для просмотра списка доступных команд");
                }
            }
        }
    }
}
