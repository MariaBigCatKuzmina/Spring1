package ru.kuzmina.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kuzmina.DAO.Dto.ProductInfoFromOrdersDto;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "price")
    private Double price;

    @Column(name = "date")
    private Date date;

    @Column(name = "quantity")
    private Integer quantity;

    public Order(Product product, User user, Double price, Date date) {
        this.product = product;
        this.user = user;
        this.price = price;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Заказ №" + id +
                ": " + product.getTitle() +
                ", user=" + user.getName() +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
