package com.kaj.app.controller;

import java.io.IOException;
import com.kaj.app.entity.User;
import com.kaj.app.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        check if session exist then redirect to index.jsp
        if (req.getSession().getAttribute("usernameSession") != null) {
            res.sendRedirect("/app/product");
        } 
        else {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    req.setAttribute("username", cookie.getValue());
                }
                if (cookie.getName().equals("password")) {
                    req.setAttribute("password", cookie.getValue());
                }
            }
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String remember = req.getParameter("remember");
        System.out.println(username + " - " + password);
        
        // username is "email"
        User user = UserService.login(username, password);
        if (user == null) {
            req.setAttribute("loginError", "Username or password is incorrect");
            req.getRequestDispatcher("login.jsp").forward(req, res);
        } else {
            if (remember != null) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
                res.addCookie(cookie);
            }
            // Set session
            req.getSession().setAttribute("usernameSession", user.getFullName());
            // Throw data for jsp view
            // req.setAttribute("username", user.getFullName());
            // req.getRequestDispatcher("home.jsp").forward(req, res);
            res.sendRedirect("/app/product");
        }
    }
}
