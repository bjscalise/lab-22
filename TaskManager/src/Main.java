import service.TaskManager;
import model.Task;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;

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
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addTask(scanner, manager);
                    case 2 -> manager.listTasks();
                   // case 3 -> markTaskComplete(scanner, manager);
                    case 4 -> deleteTask(scanner, manager);
                    case 5 -> {
                        running = false;
                        System.out.println("👋 Exiting Task Tracker. Goodbye!");
                    }
                    default -> System.out.println("⚠️ Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Invalid input. Please enter a number.");
                scanner.nextLine(); // clear invalid input
            }

        }
        scanner.close();
    }

    // 🟩 Add a new task
    private static void addTask(Scanner scanner, TaskManager manager) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate dueDate = LocalDate.parse(dateInput);

        System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
        String priority = scanner.nextLine().toUpperCase();

        manager.addTask(title, description, dueDate, priority);
    }

    // 🟦 Mark task complete
//    private static void markTaskComplete(Scanner scanner, TaskManager manager) {
//        System.out.print("Enter task ID to mark complete: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//        manager.markTaskComplete(id);
//    }

    // 🟥 Delete a task
    private static void deleteTask(Scanner scanner, TaskManager manager) {
        System.out.print("Enter task ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        manager.deleteTask(id);
    }
}

