<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm mới sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="my-4 text-center">Thêm mới sản phẩm</h2>
    <form action="?action=create" method="post">


        <!-- Tên sản phẩm -->
        <div class="mb-3">
            <label for="productName" class="form-label">Tên sản phẩm:</label>
            <input type="text" class="form-control" id="productName" name="productName" value="${name}" required>
        </div>

        <!-- Giá -->
        <div class="mb-3">
            <label for="price" class="form-label">Giá:</label>
            <input type="number" class="form-control" id="price" name="price" min="0" step="0.01" value="${price}" required>
        </div>

        <!-- Số lượng -->
        <div class="mb-3">
            <label for="quantity" class="form-label">Số lượng:</label>
            <input type="number" class="form-control" id="quantity" name="quantity" min="0" value="${quantity}" required>
        </div>

        <!-- Màu sắc -->
        <div class="mb-3">
            <label for="color" class="form-label">Màu sắc:</label>
            <input type="text" class="form-control" id="color" name="color" value="${color}">
        </div>

        <!-- Mô tả -->
        <div class="mb-3">
            <label for="description" class="form-label">Mô tả:</label>
            <textarea class="form-control" id="description" name="description" rows="3">${description}</textarea>
        </div>

        <!-- Danh mục -->
        <div class="mb-3">
            <label for="directory" class="form-label">Danh mục:</label>
            <select class="form-select" id="directory" name="directory" required>
                <c:forEach items="${categorys}" var="category">
                    <option value="${category.id}" ${category.id == category_id ? 'selected' : ''}>${category.name}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Submit and Back Buttons -->
        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-primary">Thêm mới sản phẩm</button>
            <a href="?action=" class="btn btn-secondary">Quay lại</a>
        </div>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
