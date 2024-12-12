package com.example.thi_cuoi_module_3.service;

import com.example.thi_cuoi_module_3.model.Category;
import com.example.thi_cuoi_module_3.model.Product;
import com.example.thi_cuoi_module_3.repository.IProductRepo;
import com.example.thi_cuoi_module_3.repository.ProductRepoImpl;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements IProductService{
    private IProductRepo productRepo = new ProductRepoImpl();
    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public void deleteProduct(int id) {
        productRepo.deleteProduct(id);
    }

    @Override
    public Product findProductById(int id) {
        return productRepo.findProductById(id);
    }

    @Override
    public List<Category> findAllCategory() {
        return productRepo.findAllCategory();
    }

    @Override
    public void saveProduct(String name, BigDecimal price, int quantity, String color, String description, int category_id) {
        productRepo.saveProduct(name, price, quantity, color, description, category_id);
    }
}
