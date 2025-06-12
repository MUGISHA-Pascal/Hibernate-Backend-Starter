package com.hibernate.hibernate.dao;

import com.hibernate.hibernate.models.Product;
import com.hibernate.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDao {
    public void saveProduct(Product product){
        Transaction transaction = null;
        try(Session session  = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){transaction.rollback();}
            e.printStackTrace();
        }
    }
    public Product getProduct(Long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Product.class,id);
        }
    }
    public List<Product> getAllProducts(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Product",Product.class).list();
        }
    }
    public void updateProduct(Product product){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){transaction.rollback();}
            e.printStackTrace();
        }
    }
    public void deleteProduct(Long id){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
                transaction.commit();
            }
        }catch (Exception e){
            if(transaction != null){transaction.rollback();}
            e.printStackTrace();
        }
    }
}