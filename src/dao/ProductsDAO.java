package dao;

import model.Products;

import java.math.BigDecimal;
import java.util.List;

public interface ProductsDAO {

    Products insertProducts(Products products);

    void updateProductsPrice(int id, BigDecimal newPrice);

    void updateProductsName(int id, String newName);

    void updateStock(int id, int newStock);

    void updateProducts(Products products);

    Products searchById(int id);

    void softDeleteProducts(int id);

    List<Products> listAll();
}
