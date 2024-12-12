package com.example.thi_cuoi_module_3.repository;

import com.example.thi_cuoi_module_3.model.Category;
import com.example.thi_cuoi_module_3.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductRepo {
    List<Product> findAll();
    void deleteProduct(int id);
    Product findProductById(int id);
    List<Category> findAllCategory();
    void saveProduct(String name, BigDecimal price, int quantity, String color, String description, int category_id);
}
