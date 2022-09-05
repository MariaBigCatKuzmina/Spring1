package ru.kuzmina.DAO.Dto;

public interface ProductInfoFromOrdersDto {

    Long getProductId();
    String getProductTitle();
    Double getPrice();
    Integer getQuantity();
    Long getOrderId();

}
