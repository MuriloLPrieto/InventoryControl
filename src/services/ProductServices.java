package services;

import dao.ProductsDAO;
import dao.ProductsDAOImpl;
import model.Products;

import java.math.BigDecimal;
import java.util.List;

public class ProductServices {
    private ProductsDAO dao = new ProductsDAOImpl();

    public Products CreateProducts(Products products) {
        if (products.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("ERROR! Price cannot be less than zero!");
        }

        if (products.getStock() < 0) {
            throw new IllegalArgumentException("ERROR! Stock cannot be less than zero!");
        } else if (products.getStock() == 0) {
            dao.softDeleteProducts(products.getId());
        } else {
            dao.updateStock(products.getId(), products.getStock());
        }

        return dao.insertProducts(products);
    }

    public Products listById(int id) {
        return dao.searchById(id);
    }

    public List<Products> listAll() {
        return dao.listAll();
    }

    public void updateName(Products product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Error! The product name cannot be empty or null!");
        }
        dao.updateProductsName(product.getId(), product.getName());
    }

    public void updatePrice(Products product) {
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Error! Price has to be greater than zero!");
        }
        dao.updateProductsPrice(product.getId(), product.getPrice());
    }

    public void updateStock(Products product) {
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Error! Stock has to be greater than zero!");
        }
        dao.updateStock(product.getId(), product.getStock());
    }

    public void updateProduct(Products product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Error! The product name cannot be empty or null!");
        }

        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Error! Price has to be greater than zero!");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Error! Stock has to be greater than zero!");
        }
        dao.updateProducts(product);
    }

    public void softDelete(int id) {
        Products product = dao.searchById(id);

        if (!product.isAvailable()) {
            throw new IllegalArgumentException("Error! This product is no along available");
        }

        if (product == null) {
            throw new IllegalArgumentException("Error! Product not found!");
        }

        dao.softDeleteProducts(id);
    }

}
