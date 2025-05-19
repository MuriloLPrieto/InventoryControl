package model;

import java.math.BigDecimal;

public class Products {
    private String name;
    private BigDecimal price;
    private boolean available;
    private int id, stock;

    public Products(String name, BigDecimal price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Products(String name, BigDecimal price, boolean available, int stock) {
        this.name = name;
        this.price = price;
        this.available = available;
        this.stock = stock;
    }

    public Products(String name, BigDecimal price, boolean available, int id, int stock) {
        this.name = name;
        this.price = price;
        this.available = available;
        this.id = id;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", id=" + id +
                ", stock=" + stock +
                '}';
    }
}
