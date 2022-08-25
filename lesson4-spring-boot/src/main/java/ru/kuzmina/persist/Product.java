package ru.kuzmina.persist;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class Product {
    private Long id;
    @NotBlank(message = "Наименование не может быть пустым")
    private String title;
    @Positive(message = "Стоимость не может быть нулевой")
    private Double price;

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "'" + title + "'(" + id + ")" +
                " -- " + price +
                "р. ";
    }
}

