package com.kaj.app.service;

import com.kaj.app.dao.UserDAO;
import com.kaj.app.entity.User;

public class UserService {
    private static UserDAO dao = new UserDAO();

    public static User login(String username, String password) {
        return dao.findByUserNameAndPassword(username, password);
    }

    public static User register(String fullname, String email, String pasword) {
        return dao.save(new User(fullname, email, pasword));
    }
}
