package ru.kuzmina.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotBlank(message = "Наименование не может быть пустым")

    @Column(name = "title", nullable = false)
    private String title;
//    @Positive(message = "Стоимость не может быть нулевой")

    @Column(name = "price", nullable = false)
    private Double price;

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }
    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    @Override
    public String toString() {
        return "'" + title + "'(" + id + ")" +
                " -- " + price +
                "р. ";
    }
}

