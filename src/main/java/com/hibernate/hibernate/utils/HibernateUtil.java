package com.hibernate.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.hibernate.hibernate.models.Student;
import com.hibernate.hibernate.models.Course;
import com.hibernate.hibernate.models.Vehicle;
import com.hibernate.hibernate.models.Car;
import com.hibernate.hibernate.models.Bike;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            
            // Add annotated classes
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Vehicle.class);
            configuration.addAnnotatedClass(Car.class);
            configuration.addAnnotatedClass(Bike.class);
            
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}