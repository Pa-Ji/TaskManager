package com.example.taskmanager.controller;

import com.example.taskmanager.model.TaskModel;
import com.example.taskmanager.repository.PriorityRepository;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;
    private final UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, PriorityRepository priorityRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/tasks")
    public String saveTask(@ModelAttribute TaskModel task, @RequestParam Long priorityId, @RequestParam Long userId) {
        task.setPriority(priorityRepository.findById(priorityId).orElse(null));
        task.setUser(userRepository.findById(userId).orElse(null));
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "task-list";
    }

    @GetMapping("/tasks/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new TaskModel());
        model.addAttribute("priorities", priorityRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "task-form";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        TaskModel task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Neplatné ID úkolu: " + id));
        model.addAttribute("task", task);
        model.addAttribute("priorities", priorityRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "task-form";
    }
}