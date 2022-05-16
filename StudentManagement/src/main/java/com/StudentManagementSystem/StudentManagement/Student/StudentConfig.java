package com.StudentManagementSystem.StudentManagement.Student;

import com.sun.tools.javac.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            StudentModel ashfaq = new StudentModel(
                    1L,
                    "Ashfaq",
                    "ashfakahamed11@gmail.com",
                    LocalDate.of(1999, Month.OCTOBER,19)
            );
            StudentModel ashrin = new StudentModel(
                    2L,
                    "Ashrin",
                    "ashrin11@gmail.com",
                    LocalDate.of(2003, Month.SEPTEMBER,21)
            );
            repository.saveAll(List.of(ashfaq,ashrin));
        };
    }
}
