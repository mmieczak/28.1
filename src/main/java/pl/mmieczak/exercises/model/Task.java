package pl.mmieczak.exercises.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Instant registrationDateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskEndDate;

    private LocalTime timeSpent;

    @Enumerated(value = EnumType.STRING)
    private TaskType status;

    @Enumerated(value = EnumType.STRING)
    private TaskCategory category;

    public Task() {
    }

    ;

    public Task(Long id, String name, Instant registrationDateTime, LocalDate taskEndDate, LocalTime timeSpent, TaskType status, TaskCategory category) {
        this.id = id;
        this.name = name;
        this.registrationDateTime = registrationDateTime;
        this.taskEndDate = taskEndDate;
        this.timeSpent = timeSpent;
        this.status = status;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(Instant registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    public LocalDate getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(LocalDate taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public LocalTime getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(LocalTime timeSpent) {
        this.timeSpent = timeSpent;
    }

    public TaskType getStatus() {
        return status;
    }

    public void setStatus(TaskType status) {
        this.status = status;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }
}
