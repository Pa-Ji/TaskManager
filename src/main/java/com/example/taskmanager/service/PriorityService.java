package com.example.taskmanager.service;

import com.example.taskmanager.model.TaskPriority;
import com.example.taskmanager.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityService {

    @Autowired
    private PriorityRepository priorityRepository;

    public List<TaskPriority> findAll() {
        return priorityRepository.findAll();
    }

    public TaskPriority save(TaskPriority priority) {
        return priorityRepository.save(priority);
    }

    public TaskPriority findById(Long id) {
        return priorityRepository.findById(id).orElseThrow(() -> new RuntimeException("Priorita nenalezena"));
    }

}
