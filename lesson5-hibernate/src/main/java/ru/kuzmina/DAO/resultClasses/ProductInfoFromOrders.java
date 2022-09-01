package ru.kuzmina.DAO.resultClasses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductInfoFromOrders {

    private Long productId;
    private String productTitle;
    private Double price;
    private Integer quantity;
    private Long orderId;

    public ProductInfoFromOrders(Long productId, String productTitle, Double price, Integer quantity, Long orderId) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return productTitle +
                ", " + price +
                "р., " + quantity +
                "шт., в заказе №" + orderId;
    }
}
