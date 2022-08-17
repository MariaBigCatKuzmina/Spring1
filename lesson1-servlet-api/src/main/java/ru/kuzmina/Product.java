package ru.kuzmina;

public class Product {
    private final long id;
    private final String title;
    private final double price;

    public Product(long id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}

