package com.hibernate.hibernate.example;

import com.hibernate.hibernate.dao.StudentDAO;
import com.hibernate.hibernate.dao.CourseDAO;
import com.hibernate.hibernate.models.Student;
import com.hibernate.hibernate.models.Course;
import com.hibernate.hibernate.utils.HibernateUtil;

public class HibernateExample {
    public static void main(String[] args) {
        try {
            // Initialize DAOs
            StudentDAO studentDAO = new StudentDAO();
            CourseDAO courseDAO = new CourseDAO();

            // Create a new course
            Course javaCourse = new Course();
            javaCourse.setCourseName("Java Programming");
            courseDAO.save(javaCourse);

            // Create a new student
            Student student = new Student();
            student.setFirstName("John");
            student.setLastName("Doe");
            student.setEmail("john.doe@example.com");

            // Delete any existing student with the same email
            studentDAO.findByEmail("john.doe@example.com").forEach(studentDAO::delete);

            // Save the new student
            studentDAO.save(student);

            // Enroll student in course
            studentDAO.enrollInCourse(student.getId(), javaCourse.getId());

            // Find student by email
            System.out.println("Finding student by email:");
            studentDAO.findByEmail("john.doe@example.com")
                .forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));

            // Find courses by student
            System.out.println("\nFinding courses for student:");
            courseDAO.findByStudentId(student.getId())
                .forEach(c -> System.out.println(c.getCourseName()));

            // Update student
            student.setFirstName("Johnny");
            studentDAO.update(student);

            // Find all students
            System.out.println("\nAll students:");
            studentDAO.findAll()
                .forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));

            // Find all courses
            System.out.println("\nAll courses:");
            courseDAO.findAll()
                .forEach(c -> System.out.println(c.getCourseName()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Shutdown Hibernate
            HibernateUtil.shutdown();
        }
    }
} 