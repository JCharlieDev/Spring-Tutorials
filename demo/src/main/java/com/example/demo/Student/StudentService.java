package com.example.demo.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StudentService
{
    public List<Student> GetStudents()
	{
		return List.of
		( 
			new Student(1L, "Charlie", 28, LocalDate.of(1994, Month.JANUARY, 24), "Charlie@gmail.com")
		);
	}
}
