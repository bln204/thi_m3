-- Tạo database
CREATE DATABASE  quanlysanpham;

-- Sử dụng database vừa tạo
USE quanlysanpham;

-- Tạo bảng Categories
CREATE TABLE IF NOT EXISTS Categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255)
);

-- Tạo bảng Products
CREATE TABLE IF NOT EXISTS Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255),
    price DECIMAL(15, 2),
    quantity INT,
    color VARCHAR(50),
    description TEXT,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES Categories(category_id)
);

-- Thêm dữ liệu mẫu vào bảng Categories
INSERT INTO Categories (category_name) VALUES
('Điện thoại'),
('Máy tính'),
('Phụ kiện');

-- Thêm dữ liệu mẫu vào bảng Products
INSERT INTO Products (product_name, price, quantity, color, description, category_id) VALUES
('iPhone 14', 23999.99, 100, 'Đen', 'Điện thoại thông minh Apple', 1),
('MacBook Pro', 45999.99, 50, 'Xám', 'Laptop cao cấp của Apple', 2),
('Tai nghe Bluetooth', 1999.99, 200, 'Trắng', 'Tai nghe không dây chất lượng cao', 3);

-- Tạo procedure GetAllProduct
DELIMITER //
CREATE PROCEDURE GetAllProduct()
BEGIN
    SELECT 
        p.product_id, 
        p.product_name, 
        p.price, 
        p.quantity, 
        p.color, 
        p.description, 
        c.category_name
    FROM Products p
    JOIN Categories c ON p.category_id = c.category_id;
END //
DELIMITER ;
