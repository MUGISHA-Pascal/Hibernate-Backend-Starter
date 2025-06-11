package com.hibernate.hibernate;

import com.hibernate.hibernate.example.HibernateExample;
import com.hibernate.hibernate.utils.HibernateUtil;

public class HibernateApplication {
    public static void main(String[] args) {
        try {
            // Run the example
            HibernateExample.main(args);
        } catch (Exception e) {
            System.err.println("Error running the application: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Ensure Hibernate is properly shut down
            HibernateUtil.shutdown();
        }
    }
} 