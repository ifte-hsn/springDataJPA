package com.learnwithifte.springdatajpa;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "Task")
@Table(name = "task")
public class Task {

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @Column(
            name = "due_date",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDate dueDate;

    public Task(String description, LocalDate dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
