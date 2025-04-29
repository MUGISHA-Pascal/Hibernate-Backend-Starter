package com.hibernate.hibernate.dao;

import com.hibernate.hibernate.models.Student;
import com.hibernate.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDao {
    public void saveStudent(Student student){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    public List<Student> getStudents(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
        return session.createQuery("from Student",Student.class).list();
        }
    }
}
