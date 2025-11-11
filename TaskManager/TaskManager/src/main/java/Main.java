import service.TaskManager;
import model.Task;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        boolean running = true;

        System.out.println("=== Task Tracker ===");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a new task");
            System.out.println("2. List all tasks");
            System.out.println("3. Mark task as complete");
            System.out.println("4. Delete a task");
            System.out.println("5. List tasks by priority");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");


            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> addTask(scanner, manager);
                case 2 -> manager.listTasks();
                case 3 -> markTaskComplete(scanner, manager);
                case 4 -> deleteTask(scanner, manager);
                case 5 -> manager.listTasksByPriority();
                case 6 -> {
                    running = false;
                    System.out.println("👋 Exiting Task Tracker. Goodbye!");
                }
                default -> System.out.println("⚠️ Invalid choice. Please try again.");
            }

        }

        scanner.close();

    }

    // Validate the integer input
    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Please enter a valid number: ");
            }
        }
    }

    private static void addTask(Scanner scanner, TaskManager manager) {
        System.out.print("Enter task title: ");
        String title = "";
        while (title.trim().isEmpty()) {
            System.out.print("Enter task title: ");
            title = scanner.nextLine().trim();
            if (title.trim().isEmpty()) {
                System.out.println("⚠️ Title cannot be empty. Please enter a valid title.");
            }
        }
        title = title.trim();

        String description = "";
        while (description.trim().isEmpty()) {
            System.out.print("Enter task description: ");
            description = scanner.nextLine().trim();
            if (description.trim().isEmpty()) {
                System.out.println("⚠️ Description cannot be empty. Please enter a valid description.");
            }
        }
        description = description.trim();

        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.print("Enter due date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine().trim();
            try {
                dueDate = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Invalid date format. Please use YYYY-MM-DD (e.g., 2025-11-05).");
            }
        }

        Task.Priority priority = null;
        while (priority == null) {
            System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                priority = Task.Priority.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Invalid priority. Please enter LOW, MEDIUM, or HIGH.");
            }
        }

        manager.addTask(title, description, dueDate, priority);
    }

    //Mark task complete
    private static void markTaskComplete(Scanner scanner, TaskManager manager) {
        System.out.print("Enter task ID to mark complete: ");
        int id = readInt(scanner);
        manager.markTaskComplete(id);
    }

    // Delete a task
    private static void deleteTask(Scanner scanner, TaskManager manager) {
        System.out.print("Enter task ID to delete: ");
        int id = readInt(scanner);
        manager.deleteTask(id);
    }
}

