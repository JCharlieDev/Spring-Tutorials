package com.example.demo.Student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Optional<Student> studentOptional =
                studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent())
        {
            throw new IllegalStateException("email already taken");
        }

        studentRepository.save(student);
    }

    public void DeleteStudent(Long studentId)
    {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists)
        {
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void UpdateStudent(Long studentId, String name, String email)
    {
        Student student = studentRepository.
        findById(studentId).
        orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name))
        {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email))
        {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if (studentOptional.isPresent())
            {
                throw new IllegalStateException("email taken");
            }

            student.setEmail(email);
        }

    }
}
