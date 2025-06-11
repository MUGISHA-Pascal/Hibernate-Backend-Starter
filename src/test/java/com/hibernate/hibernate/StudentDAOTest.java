package com.hibernate.hibernate;

import com.hibernate.hibernate.dao.StudentDAO;
import com.hibernate.hibernate.models.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentDAOTest {
    private StudentDAO studentDAO;

    @BeforeEach
    void setUp() {
        studentDAO = new StudentDAO();
    }

    @AfterEach
    void tearDown() {
        // Clean up any test data if needed
    }

    @Test
    void testSaveAndFindStudent() {
        // Create a new student
        Student student = new Student();
        student.setFirstName("Test");
        student.setLastName("User");
        student.setEmail("test.user@example.com");

        // Save the student
        studentDAO.save(student);
        assertNotNull(student.getId(), "Student ID should not be null after saving");

        // Find the student
        Student foundStudent = studentDAO.findById(student.getId());
        assertNotNull(foundStudent, "Student should be found");
        assertEquals("Test", foundStudent.getFirstName(), "First name should match");
        assertEquals("User", foundStudent.getLastName(), "Last name should match");
        assertEquals("test.user@example.com", foundStudent.getEmail(), "Email should match");
    }

    @Test
    void testFindByEmail() {
        // Create and save a test student
        Student student = new Student();
        student.setFirstName("Email");
        student.setLastName("Test");
        student.setEmail("email.test@example.com");
        studentDAO.save(student);

        // Find by email
        var foundStudents = studentDAO.findByEmail("email.test@example.com");
        assertFalse(foundStudents.isEmpty(), "Should find student by email");
        assertEquals("email.test@example.com", foundStudents.get(0).getEmail(), "Email should match");
    }

    @Test
    void testUpdateStudent() {
        // Create and save a test student
        Student student = new Student();
        student.setFirstName("Update");
        student.setLastName("Test");
        student.setEmail("update.test@example.com");
        studentDAO.save(student);

        // Update the student
        student.setFirstName("Updated");
        studentDAO.update(student);

        // Find and verify the update
        Student updatedStudent = studentDAO.findById(student.getId());
        assertEquals("Updated", updatedStudent.getFirstName(), "First name should be updated");
    }

    @Test
    void testDeleteStudent() {
        // Create and save a test student
        Student student = new Student();
        student.setFirstName("Delete");
        student.setLastName("Test");
        student.setEmail("delete.test@example.com");
        studentDAO.save(student);

        // Delete the student
        studentDAO.delete(student);

        // Verify deletion
        Student deletedStudent = studentDAO.findById(student.getId());
        assertNull(deletedStudent, "Student should be deleted");
    }
} 