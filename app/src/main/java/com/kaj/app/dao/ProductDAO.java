package com.kaj.app.dao;

import org.hibernate.Session;

import com.kaj.app.entity.Product;
import com.kaj.util.HibernateUtil;

public class ProductDAO implements Repository<Product, Integer> {
    private static final Session session = HibernateUtil.getFactory().openSession();

    @Override
    public Product save(Product entity) {
        try {
            session.beginTransaction();
            Product product = session.createQuery("from Product where name = :name", Product.class)
                    .setParameter("name", entity.getName())
                    .getSingleResultOrNull();

            if (product == null) {
                System.out.println("Product is not exist");
                session.persist(entity);
                session.getTransaction().commit();
                return entity;
            } else {
                System.out.println("Product is exist");
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
    public Product findById(Integer integer) {
        try {
            session.beginTransaction();
            Product product = session.createQuery("from Product where id = :id", Product.class)
                    .setParameter("id", integer)
                    .getSingleResult();
            session.getTransaction().commit();
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Iterable<Product> findAll() {
        try {
            session.beginTransaction();
            Iterable<Product> products = session.createQuery("from Product", Product.class).list();
            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void delete(Product entity) {
        try {
            session.beginTransaction();
            session.remove(entity);;
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void deleteById(Integer integer) {
        try {
            session.beginTransaction();
            Product product = session.createQuery("from Product where id = :id", Product.class)
                    .setParameter("id", integer)
                    .getSingleResult();
            session.remove(product);;
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public Product update(Product entity) {
        return null;
    }
}
