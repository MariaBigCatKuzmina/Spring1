package ru.kuzmina.model.Dto;

public interface ProductInfoFromOrdersDto {

    Long getProductId();
    String getProductTitle();
    Double getPrice();
    Integer getQuantity();
    Long getOrderId();

}
