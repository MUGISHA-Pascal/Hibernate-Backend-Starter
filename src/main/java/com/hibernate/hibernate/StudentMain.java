package com.hibernate.hibernate;

import com.hibernate.hibernate.dao.StudentDao;
import com.hibernate.hibernate.models.Student;

import java.util.List;

public class StudentMain {
    public static void main(String[] args){
        StudentDao studentdao = new StudentDao();
        Student student =  new Student("John","doe","john@gmail.com");
       studentdao.saveStudent(student);
        List<Student>  students = studentdao.getStudents();
        for(Student s:students){
            System.out.println("student: " + s.getFirstName());
        }
    }
}
