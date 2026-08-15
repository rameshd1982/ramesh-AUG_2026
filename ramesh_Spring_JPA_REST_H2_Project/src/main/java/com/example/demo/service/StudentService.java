package com.example.demo.service;

import com.example.demo.dto.StudentRequest;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(StudentRequest request) {

        // Check for duplicate email
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Student with email already exists: " + request.getEmail());
        }

        // Map DTO → Entity
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setCourse(request.getCourse());
        student.setAge(request.getAge());

        // Save to H2
        return studentRepository.save(student);
    }
}