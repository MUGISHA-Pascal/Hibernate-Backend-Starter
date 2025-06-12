package com.hibernate.hibernate.dao;

import com.hibernate.hibernate.models.Product;
import com.hibernate.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);

    public void saveProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
            logger.info("Product saved successfully: {}", product.getName());
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            logger.error("Error saving product: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save product", e);
        }
    }

    public Optional<Product> getProduct(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product product = session.get(Product.class, id);
            return Optional.ofNullable(product);
        } catch (Exception e) {
            logger.error("Error retrieving product with id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve product", e);
        }
    }

    public List<Product> getAllProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Product", Product.class).list();
        } catch (Exception e) {
            logger.error("Error retrieving all products: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve products", e);
        }
    }

    public void updateProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();
            logger.info("Product updated successfully: {}", product.getName());
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            logger.error("Error updating product: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to update product", e);
        }
    }

    public void deleteProduct(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.remove(product);
                transaction.commit();
                logger.info("Product deleted successfully with id: {}", id);
            } else {
                logger.warn("No product found with id: {}", id);
            }
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            logger.error("Error deleting product: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to delete product", e);
        }
    }
}