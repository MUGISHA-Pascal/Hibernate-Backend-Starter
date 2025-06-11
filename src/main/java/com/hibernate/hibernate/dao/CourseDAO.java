package com.hibernate.hibernate.dao;

import com.hibernate.hibernate.models.Course;
import com.hibernate.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class CourseDAO extends GenericDAOImpl<Course> {
    public CourseDAO() {
        super(Course.class);
    }

    public List<Course> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Course> query = session.createQuery(
                "from Course c where c.name like :name", 
                Course.class
            );
            query.setParameter("name", "%" + name + "%");
            return query.list();
        }
    }

    public List<Course> findByStudentId(Long studentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Course> query = session.createQuery(
                "select c from Course c join c.students s where s.id = :studentId", 
                Course.class
            );
            query.setParameter("studentId", studentId);
            return query.list();
        }
    }
} 