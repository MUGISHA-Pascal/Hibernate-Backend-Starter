package com.hibernate.hibernate.dao;

import com.hibernate.hibernate.models.Product;
import com.hibernate.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

}