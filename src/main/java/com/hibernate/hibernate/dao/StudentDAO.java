package com.hibernate.hibernate.dao;

import com.hibernate.hibernate.models.Student;
import com.hibernate.hibernate.models.Course;
import com.hibernate.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class StudentDAO extends GenericDAOImpl<Student> {
    public StudentDAO() {
        super(Student.class);
    }

    public List<Student> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery(
                "from Student s where s.email = :email", 
                Student.class
            );
            query.setParameter("email", email);
            return query.list();
        }
    }

    public List<Student> findByCourse(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery(
                "select s from Student s join s.courses c where c = :course", 
                Student.class
            );
            query.setParameter("course", course);
            return query.list();
        }
    }

    public void enrollInCourse(Long studentId, Long courseId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            
            Student student = session.get(Student.class, studentId);
            Course course = session.get(Course.class, courseId);
            
            if (student != null && course != null) {
                student.addCourse(course);
                session.merge(student);
            }
            
            session.getTransaction().commit();
        }
    }
} 