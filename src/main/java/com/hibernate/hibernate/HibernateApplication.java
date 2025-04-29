package com.hibernate.hibernate;

import com.hibernate.hibernate.models.Bike;
import com.hibernate.hibernate.models.Car;
import com.hibernate.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class HibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Car car = new Car();
        car.setManufacturer("Benz");
        car.setNumOfdoors(5);
        session.persist(car);

        Bike bike = new Bike();
        bike.setManufacturer("Samsung");
        bike.setHasCarrier(true);
        session.persist(bike);

        transaction.commit();

        session.close();
    }
}
