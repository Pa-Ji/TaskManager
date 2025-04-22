package com.example.taskmanager.controller;

import com.example.taskmanager.model.TaskModel;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "task-list";
    }

    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new TaskModel());
        return "task-form";
    }

    @PostMapping
    public String saveTask(@ModelAttribute TaskModel task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        TaskModel task = taskService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neplatné ID úkolu: " + id));
        model.addAttribute("task", task);
        return "task-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }

    @GetMapping("/filter")
    public String filterByPriority(@RequestParam String priority, Model model) {
        TaskRepository taskRepository = getTaskRepository();
        assert taskRepository != null;
        List<TaskModel> filteredTasks = taskRepository.findByPriority(priority);
        model.addAttribute("tasks", filteredTasks);
        return "task-list";
    }

    private static TaskRepository getTaskRepository() {
        return null;
    }

}