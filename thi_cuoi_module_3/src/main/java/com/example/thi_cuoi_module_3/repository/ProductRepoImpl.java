package com.example.thi_cuoi_module_3.repository;

import com.example.thi_cuoi_module_3.common.BaseRepository;
import com.example.thi_cuoi_module_3.model.Category;
import com.example.thi_cuoi_module_3.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ProductRepoImpl implements IProductRepo{

    private static BaseRepository baseRepository;
    static {
        baseRepository = new BaseRepository();
    }

    private static final String FIND_ALL = "CALL GetAllProduct()";
    private static final String FIND_BY_ID ="CALL GetProductById(?)";
    @Override
    public List<Product> findAll() {
        Connection connection = baseRepository.getConnection();
        List<Product> list = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                String category_name = resultSet.getString("category_name");
                Product product = new Product(id,name,price,quantity,color,description, new Category(category_id, category_name));
                list.add(product);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list ;
    }

    @Override
    public void deleteProduct(int id) {
        Connection connection = baseRepository.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE product_id =?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Product findProductById(int id) {
        Connection connection = baseRepository.getConnection();
        Product product = null;
        try{
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                BigDecimal price = resultSet.getBigDecimal("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");
                product = new Product(productId, productName, price, quantity, color, description, new Category(categoryId, categoryName));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Category> findAllCategory() {
        Connection connection = baseRepository.getConnection();
        List<Category> list = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("category_id");
                String name = resultSet.getString("category_name");
                list.add(new Category(id, name));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void saveProduct(String name, BigDecimal price, int quantity, String color, String description, int category_id) {
        Connection connection = baseRepository.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products (product_name, price, quantity, color, description, category_id) VALUES (?,?,?,?,?,?)");
            statement.setString(1, name);
            statement.setBigDecimal(2, price);
            statement.setInt(3, quantity);
            statement.setString(4, color);
            statement.setString(5, description);
            statement.setInt(6, category_id);
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
