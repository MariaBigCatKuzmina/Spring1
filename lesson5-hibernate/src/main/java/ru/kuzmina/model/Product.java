package ru.kuzmina.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;


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

    //это сделала чтобы попробовать связь многие-ко-многим
    @ManyToMany
    @JoinTable(name = "Products_Users",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private List<User> users;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public Product(String title, Double price, List<User> users) {
        this(title, price);
        this.users = users;
    }

    @Override
    public String toString() {
        return "(" + id + ") '" +
                title +
                "' -- " + price +
                "р. ";
    }
}
