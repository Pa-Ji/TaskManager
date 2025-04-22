package com.example.taskmanager.controller;

import com.example.taskmanager.model.TaskModel;
import com.example.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;


    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", service.getAllTasks());
        return "task-list";
    }

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new TaskModel());
        return "task-form";
    }

    @PostMapping
    public String saveTask(@ModelAttribute TaskModel task) {
        service.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        return service.getById(id)
                .map(task -> {
                    model.addAttribute("task", task);
                    return "task-form";
                })
                .orElse("redirect:/tasks");
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/tasks";
    }
}