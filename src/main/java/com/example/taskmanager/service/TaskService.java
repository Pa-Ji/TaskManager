package com.example.taskmanager.service;

import com.example.taskmanager.model.TaskModel;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }

    public TaskModel findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void save(TaskModel task) {
        taskRepository.save(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
