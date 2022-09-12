package ru.kuzmina.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private Double price;

    public ProductDto(String title, Double price) {
        this.title = title;
        this.price = price;
    }
}
