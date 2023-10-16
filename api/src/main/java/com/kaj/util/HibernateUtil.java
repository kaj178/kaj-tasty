package com.kaj.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.kaj.api.entity.Product;
import com.kaj.api.entity.User;

public class HibernateUtil {
    private static final SessionFactory FACTORY;

    static {
        Configuration config = new Configuration();

        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(User.class);
        config.addAnnotatedClass(Product.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
            .applySettings(config.getProperties())
            .build();
        FACTORY = config.buildSessionFactory(registry);
    }

    public static SessionFactory getFactory() {
        return FACTORY;
    }
}
