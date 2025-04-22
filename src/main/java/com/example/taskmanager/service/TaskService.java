package com.example.taskmanager.service;

import com.example.taskmanager.model.TaskModel;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskModel> getAllTasks() {
        return repository.findAll();
    }

    public Optional<TaskModel> getById(Long id) {
        return repository.findById(id);
    }

    public void save(TaskModel task) {
        repository.save(task);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<TaskModel> getTasksByPriority(String priorityName) {
        return repository.findByPriorityName(priorityName);
    }

    public List<TaskModel> getTasksByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}