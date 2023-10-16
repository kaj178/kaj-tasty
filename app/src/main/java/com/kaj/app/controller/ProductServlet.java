package com.kaj.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.kaj.app.entity.Product;
import com.kaj.app.service.ProductService;

// @WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private static final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        /product?action=del&id=1
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        if (action != null && action.equals("del") && id != null) {
            Product product = productService.findById(Integer.parseInt(id));
            if (product != null) {
                productService.delete(product);
                req.setAttribute("message", "Delete product successfully");
                req.setAttribute("type", "success");
            } 
            else {
                req.setAttribute("message", "Cannot delete product");
                req.setAttribute("type", "danger");
            }
        }
        List<Product> products = (List<Product>) productService.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("home.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        Integer price = Integer.parseInt(req.getParameter("price").trim());

        Product product = productService.save(new Product(name, price));
        if (product == null) {
            req.setAttribute("message", "Cannot add product");
            req.setAttribute("type", "danger");
        } else {
            req.setAttribute("message", "Add product successfully");
            req.setAttribute("type", "success");
        }

        List<Product> products = (List<Product>) productService.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("home.jsp").forward(req, res);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        super.doPut(req, res);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        super.doDelete(req, res);
    }
}