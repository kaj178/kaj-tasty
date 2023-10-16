package com.kaj.app.dao;

import com.kaj.app.entity.User;
import com.kaj.app.service.UserService;

public class UserDAO {
    private static UserService service = new UserService();

    public static User login(String username, String password) {
        return service.findByUserNameAndPassword(username, password);
    }

    public static User register(String fullname, String email, String pasword) {
        return service.save(new User(fullname, email, pasword));
    }
}
