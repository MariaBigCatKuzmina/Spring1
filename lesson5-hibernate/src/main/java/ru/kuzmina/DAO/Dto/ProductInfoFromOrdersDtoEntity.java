package ru.kuzmina.DAO.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductInfoFromOrdersDtoEntity implements ProductInfoFromOrdersDto {

    private Long productId;
    private String productTitle;
    private Double price;
    private Integer quantity;
    private Long orderId;

    @Override
    public String toString() {
        return productId +
                ". '" + productTitle + '\'' +
                ", " + price +
                "р., " + quantity +
                "шт., № заказа " + orderId;
    }
}
