package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import model.LocalDateAdapter;
import model.Task;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "tasks.json";

    public TaskManager() {
        loadTasksFromFile(FILE_PATH);
    }

    //Add a new task
    public void addTask(String title, String description, LocalDate dueDate, String priority) {
        int id = tasks.size() + 1;
        tasks.add(new Task(id, title, description, dueDate, priority, false));
        saveTasksToFile(FILE_PATH);
//        System.out.println("✅ Task added: " + newTask.getTitle());
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("⚠️ No tasks found.");
        } else {
            tasks.forEach(System.out::println);
        }
    }

    public void markTaskComplete(int id) {
        Optional<Task> taskOpt = findTaskById(id);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setCompleted(true);
            System.out.println("✅ Task marked as complete: " + task.getTitle());
            saveTasksToFile(FILE_PATH);
        } else {
            System.out.println("⚠️ Task not found.");
        }
    }


    // 🟥 Delete a task by ID
    public void deleteTask(int id) {
        tasks.removeIf(t -> t.getId() == id);
        System.out.println("🗑️ Task deleted.");
        saveTasksToFile(FILE_PATH);
    }

    private Optional<Task> findTaskById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    private void saveTasksToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
            gson.toJson(tasks, writer);
            System.out.println("💾 Tasks saved to file!");
        } catch (IOException e) {
            System.out.println("⚠️ Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTasksFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            tasks = new ArrayList<>();
            return;
        }

        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
            Type taskListType = new TypeToken<List<Task>>() {}.getType();

            try {
                tasks = gson.fromJson(reader, taskListType);
            } catch (com.google.gson.JsonSyntaxException e) {
                System.out.println("⚠️ JSON file is empty or invalid. Starting with empty task list.");
                tasks = new ArrayList<>();
            }

            if (tasks == null) {
                tasks = new ArrayList<>();
            }

            System.out.println("📂 Loaded " + tasks.size() + " tasks from file.");

        } catch (IOException e) {
            System.out.println("⚠️ Error reading tasks file: " + e.getMessage());
            tasks = new ArrayList<>();
        }
    }
}