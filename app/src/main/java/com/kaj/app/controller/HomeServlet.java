package com.kaj.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getSession().getAttribute("usernameSession") != null) {
            res.sendRedirect("/app/product");
        } 
        if (req.getSession().getAttribute("usernameSession") == null) {
            System.out.println("User's fullname: " + req.getSession().getAttribute("usernameSession"));
            System.out.println("redirect to login");
            res.sendRedirect("/app/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}