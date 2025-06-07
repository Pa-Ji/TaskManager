package com.example.taskmanager.controller;

import com.example.taskmanager.model.TaskModel;
import com.example.taskmanager.model.TaskPriority;
import com.example.taskmanager.service.PriorityService;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private PriorityService priorityService;

    @GetMapping("/home")
    public String taskList(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "task-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new TaskModel());
        model.addAttribute("priorities", priorityService.findAll());
        return "task-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TaskModel task = taskService.findById(id);
        model.addAttribute("task", task);
        model.addAttribute("priorities", priorityService.findAll());
        return "task-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") TaskModel task) {
        try {
            // 👉 Ladicí výpisy:
            System.out.println("Zvolená priorita: " + task.getPriority());
            System.out.println("Zvolená priorita ID: " + (task.getPriority() != null ? task.getPriority().getId() : "null"));

            // najdi prioritu podle ID a nastav ji správně
            TaskPriority priority = priorityService.findById(task.getPriority().getId());
            task.setPriority(priority);

            taskService.save(task);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace(); // vypíše chybu do konzole
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/home";
    }
}
