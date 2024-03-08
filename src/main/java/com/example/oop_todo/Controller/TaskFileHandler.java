package com.example.oop_todo.Controller;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class TaskFileHandler {

    public static List<Task> readTasksFromFile(String filename) {
        List<Task> tasks = new ArrayList<>();
        Path path = Paths.get(filename);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromString(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return tasks;
    }

    public static void writeTasksToFile(List<Task> tasks, String filename) {
        Path path = Paths.get(filename);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
