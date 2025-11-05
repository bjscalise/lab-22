package service;

import model.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    //Add a new task
    public void addTask(String title, String description, LocalDate dueDate, String priority) {
        Task newTask = new Task(nextId++, title, description, dueDate, priority);
        tasks.add(newTask);
        System.out.println("✅ Task added: " + newTask.getTitle());
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        System.out.println("\n--- Task List ---");
        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    //Find a task by its ID (returns optional)
    private Optional<Task> findTaskById(int id){
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    //Mark a task as complete
//    public void markTaskComplete(int id){
//        Optional<Task> taskOpt = findTaskById(id);
//        if (taskOpt.isPresent()){
//            Task task = taskOpt.get();
//            task.markTaskComplete();
//            System.out.println("✅ Task marked as complete: " + task.getTitle());
//        } else {
//            System.out.println("⚠️ Task with ID " + id + " not found.");
//        }
//    }
    // 🟥 Delete a task by ID
    public void deleteTask(int id) {
        Optional<Task> taskOpt = findTaskById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            tasks.remove(task);
            System.out.println("🗑️ Task deleted: " + task.getTitle());
        } else {
            System.out.println("⚠️ Task with ID " + id + " not found.");
        }
    }

    // (Optional) 🧹 Clear all tasks
    public void clearAllTasks() {
        tasks.clear();
        nextId = 1;
        System.out.println("All tasks cleared.");
    }
}