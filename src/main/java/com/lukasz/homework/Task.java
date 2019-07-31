package com.lukasz.homework;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String task;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;
    @Enumerated(EnumType.STRING)
    private TaskCategory category;
    private String isDone;

    public Task(String task, LocalDate toDate, TaskCategory category, String isDone) {
        this.task = task;
        this.toDate = toDate;
        this.category = category;
        this.isDone = isDone;
    }

    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }
}
