package com.kaj.app.controller;

import java.io.IOException;

import com.kaj.app.entity.User;
import com.kaj.app.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getSession().getAttribute("username") != null) {
            req.getRequestDispatcher("home.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("register.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String fullname = req.getParameter("fullname").trim();
        String email = req.getParameter("email").trim();
        String password = req.getParameter("password").trim();
        String confirmPassword = req.getParameter("confirm-password").trim();

        if (fullname.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
            req.setAttribute("registerError", "Please fill all fields");
            req.getRequestDispatcher("register.jsp").forward(req, res);
        } 
        else if (!password.equals(confirmPassword)) {
            req.setAttribute("registerError", "Password and confirm password must be the same");
            req.getRequestDispatcher("register.jsp").forward(req, res);
        } 
        else {
            User user = UserService.register(fullname, email, password);
            if (user == null) {
                req.setAttribute("registerError", "Username or email already exists");
                req.getRequestDispatcher("register.jsp").forward(req, res);
            } 
            else {
                // Set session
                req.getSession().setAttribute("usernameSession", user.getFullName());
                req.setAttribute("username", email);
                req.getRequestDispatcher("home.jsp").forward(req, res);
            }
        }
    }
}
