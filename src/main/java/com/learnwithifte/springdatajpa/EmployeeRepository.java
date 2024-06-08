package com.learnwithifte.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT e FROM Employee e WHERE e.email = ?1")
    Optional<Employee> findEmployeeByEmail(String email);

    @Query(
            value = "SELECT e FROM Employee e WHERE e.firstName = :firstName and e.lastName = :lastName")
    List<Employee> findEmployeesByFirstNameEqualsAndLastNameEquals(
            @Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(
            value = "SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findEmployeesBySalaryGreaterThan(@Param("salary") double salary);

    @Query(
            value = "SELECT e FROM Employee e WHERE e.hireDate > :hireDate")
    List<Employee> findEmployeesByHireDateAfter(@Param("hireDate") LocalDate hireDate);
}
