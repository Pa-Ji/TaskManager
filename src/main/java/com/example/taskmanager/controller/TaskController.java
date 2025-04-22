package com.example.taskmanager.controller;

import com.example.taskmanager.model.TaskModel;
import com.example.taskmanager.repository.PriorityRepository;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PriorityRepository priorityRepository;

    // Domovská stránka – seznam úkolů
    @GetMapping("/")
    public String showTaskList(Model model) {
        List<TaskModel> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    // Formulář pro vytvoření nového úkolu
    @GetMapping("/create")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new TaskModel());
        model.addAttribute("priorities", priorityRepository.findAll());
        return "task-form";
    }

    // Formulář pro úpravu existujícího úkolu
    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neplatné ID úkolu: " + id));
        model.addAttribute("task", task);
        model.addAttribute("priorities", priorityRepository.findAll());
        return "task-form";
    }

    // Uložení nebo aktualizace úkolu
    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") TaskModel task) {
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/toggle-completed/{id}")
    public String toggleCompleted(@PathVariable Long id) {
        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Neplatné ID úkolu: " + id));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
        return "redirect:/";
    }

    // Smazání úkolu
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }
}
