package com.example.demo.Student;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService
{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    public List<Student> GetStudents()
    {
        return studentRepository.findAll(); // Returns a list of student
    }

    public void AddNewStudent(Student student)
    {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent())
        {
            throw new IllegalStateException("email already taken");
        }

        studentRepository.save(student);
    }
}
