package com.example.thi_cuoi_module_3.controller;

import com.example.thi_cuoi_module_3.model.Product;
import com.example.thi_cuoi_module_3.service.IProductService;
import com.example.thi_cuoi_module_3.service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name ="ProductServlet", value="/")
public class ProductServlet extends HttpServlet {
    private IProductService productService;
    public void init(){
        productService = new ProductServiceImpl();  // Load data from database here.  For simplicity, we just create a mock service.  In a real-world scenario, you would use a dependency injection framework like Spring or Guice.  But for this simple example, we're using a mock service.  This would be replaced with an actual implementation in a real-world application.'
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null ){
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "edit":
                break;
            default:
                showProductList(request, response);
        }
    }

    private void showProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");
        request.setAttribute("categorys",productService.findAllCategory());
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null ){
            action = "";
        }
        switch (action) {
            case "create":
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "edit":
                break;

        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProductById(id);
        if(product!= null){
            productService.deleteProduct(id);
        } try{
            response.sendRedirect(request.getContextPath() + "/");
        } catch( IOException e){
            e.printStackTrace();
        }
    }
}
