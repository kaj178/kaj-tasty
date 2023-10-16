package com.kaj.app.service;

import org.hibernate.Session;

import com.kaj.app.entity.User;
import com.kaj.util.HibernateUtil;

public class UserService implements Repository<User, Integer> {
    private static final Session session = HibernateUtil.getFactory().openSession();

    public User findByUserNameAndPassword(String username, String password) {
        session.beginTransaction();

        try {
            User user = session.createQuery("from User where email = :email and password = :password", User.class)
                    .setParameter("email", username)
                    .setParameter("password", password)
                    .getSingleResult();
            session.getTransaction().commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
    @Override
    public User save(User user) {
        try {
            session.beginTransaction();
            User newUser = session.createQuery("from User where email = :email", User.class)
                    .setParameter("email", user.getEmail())
                    .uniqueResult();
            // User newUser = session.get
            if (newUser == null) {
                // System.out.println("Username and password are not exist");
                session.persist(newUser);
                session.getTransaction().commit();
                return user;
            } else {
                session.getTransaction().rollback();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public User findById(Integer integer) {
        return session.createQuery("from User where id = :id", User.class)
                .setParameter("id", integer)
                .getSingleResult();
    }

    @Override
    public Iterable<User> findAll() {
        return session.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void delete(User entity) {
        session.beginTransaction();
        try {
            session.remove(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void deleteById(Integer integer) {
        session.beginTransaction();
        try {
            session.createQuery("delete from User where id = :id", User.class)
                    .setParameter("id", integer)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
