package com.example.demo.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig
{


    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {

        return args -> 
        {
            Student charlie = new Student("Charlie", LocalDate.of(1994, Month.JANUARY, 24), "Charlie@gmail.com");
            Student rodrigo = new Student("Rodrigo", LocalDate.of(1992, Month.JANUARY, 24), "Rodrigo@gmail.com");
            
            repository.saveAll(List.of(charlie, rodrigo));
        };

    }
}
