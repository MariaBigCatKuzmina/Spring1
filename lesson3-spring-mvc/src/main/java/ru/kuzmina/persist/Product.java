package ru.kuzmina.persist;

public class Product {
    private Long id;
    private String title;
    private Double price;

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "'" + title + "'(" + id + ")" +
                " -- " + price +
                "р. ";
    }
}

