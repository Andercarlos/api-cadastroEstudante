package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StundentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
           Student andressa = new Student(
                    "Andressa Albania",
                    "andressa@gmail.com",
                    LocalDate.of(1994, Month.DECEMBER,15),
                    26);
            Student joao = new Student(
                    "João Lucas",
                    "joao@gmail.com",
                    LocalDate.of(2003,Month.JANUARY,6),18
            );

            repository.saveAll(List.of(andressa,joao));

        };

    }
}
