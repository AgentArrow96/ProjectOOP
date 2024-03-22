package com.example.oop_todo.Controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Comparator;

public class TaskList implements Persistable{
    private List<Task> tasks;
    private final String filename;

    // Constructor
    public TaskList(String filename) {
        this.filename = filename;
        this.tasks = new ArrayList<>();
        load();
    }

    // Implementing the Persistable interface methods
    @Override
    public void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename , true))) {
            for (Task task : tasks) {
                bw.write(task.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Skip empty lines or lines that do not have the expected format
                if (!line.trim().isEmpty() && line.split(",").length == 6) {
                    try {
                        tasks.add(Task.fromString(line));
                    } catch (Exception e) {
                        System.err.println("Skipping invalid line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Additional methods for task list manipulation
    // Generate a unique ID for a new task
    private long generateUniqueID() {
        if (tasks.isEmpty()) {
            return 1; // Start IDs from 1 if no tasks exist
        } else {
            // Find the maximum ID and add 1 to it to ensure uniqueness
            return tasks.stream()
                    .max(Comparator.comparingLong(Task::getId))
                    .map(task -> task.getId() + 1)
                    .orElse(1L);
        }
    }

    public void addTask(String title, String description, LocalDate dueDate, boolean isPriority) {
        // Load tasks from file to ensure we have the latest list and to compute the next unique ID accurately
        load();
        long uniqueId = generateUniqueID(); // Generate a unique ID
        Task newTask = new Task(uniqueId, title, description, dueDate, isPriority);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(newTask.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //NOT USED YET
    public void updateTask(long id, String title, String description, LocalDate dueDate, boolean isPriority) {
        // Find the task by ID
        for (Task task : tasks) {
            if (task.getId() == id) {
                // Update task properties
                task.setTitle(title);
                task.setDescription(description);
                task.setDueDate(dueDate);
                task.setPriority(isPriority);
                break; // Exit the loop once the task is found and updated
            }
        }
        save(); // Save the updated tasks list to the file
    }


    //NOT USED YET
    public Task getTaskById(long id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null; // Return null or throw an exception if the task is not found
    }


    public void removeTask(Task task) {
        tasks.remove(task);
        save();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}
