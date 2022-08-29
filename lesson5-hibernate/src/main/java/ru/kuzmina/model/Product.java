package ru.kuzmina.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Наименование не может быть пустым")
    @Column(nullable = false, unique = true)
    private String title;

    @Positive(message = "Стоимость не может быть нулевой")
    @Column(nullable = false)
    private Double price;

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "(" + id + ") '" +
                title +
                "' -- " + price +
                "р. ";
    }
}
