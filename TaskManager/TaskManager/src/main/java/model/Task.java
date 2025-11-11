package model;

import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }
    private boolean completed;

    public Task(int id, String title, String description, LocalDate dueDate, Priority priority, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;  // default when a new task is created
    }


    //GETTERS
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }


    //SETTERS


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void markAsCompleted(){
        this.completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    // Print toString() for displaying the task in console output
    @Override
    public String toString() {
        return String.format("[%d] %s | Due: %s | Priority: %s | Status: %s",
                id,
                title,
                dueDate,
                priority,
                (completed ? "✅ Done" : "❌ Pending"));

    }
}
