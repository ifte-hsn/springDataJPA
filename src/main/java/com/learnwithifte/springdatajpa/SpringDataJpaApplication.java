package com.learnwithifte.springdatajpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
        return args -> {
            Employee john = new Employee(
                    "John",
                    "Doe",
                    "john@doe.com",
                    LocalDate.of(2020, 1, 1),
                    2000.00
            );

            employeeRepository.save(john);

            Employee jane = new Employee(
                    "Jane",
                    "Doe",
                    "jane@doe.com",
                    LocalDate.of(2022, 1, 1),
                    2300.00
            );

            Employee richard = new Employee(
                    "Richard",
                    "Roe",
                    "Richard@roe.com",
                    LocalDate.of(2022, 1, 1),
                    5000.00
            );

            employeeRepository.saveAll(List.of(jane, richard));

            employeeRepository.findById(1L).ifPresentOrElse(
                    System.out::println,
                    () -> {
                        System.out.println("Employee not found");
                    }
            );

            // Find all employee
            List<Employee> employeeList = employeeRepository.findAll();
            employeeList.forEach(System.out::println);

            // delete employee
            employeeRepository.deleteById(1L);

            generateEmployee(employeeRepository);

        };
    }


    private void generateEmployee(EmployeeRepository employeeRepository) {
        Faker faker = new Faker();

        for(int i = 0; i < 20; i++) {
            Employee employee = new Employee(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress(),
                    LocalDate.of(faker.number().numberBetween(2018, 2024), faker.number().numberBetween(1, 12), faker.number().numberBetween(1,28)),
                    faker.number().randomDouble(2,1000,5000)
            );

            employeeRepository.save(employee);

        }
    }
}
