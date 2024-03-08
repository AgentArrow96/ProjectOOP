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
                if (!line.trim().isEmpty() && line.split(",").length == 5) { // Assuming 5 fields: id, title, description, dueDate, isPriority
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
        long uniqueId = generateUniqueID(); // Generate a unique ID
        Task newTask = new Task(uniqueId, title, description, dueDate, isPriority);
        tasks.add(newTask);
        save();
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        save();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}
