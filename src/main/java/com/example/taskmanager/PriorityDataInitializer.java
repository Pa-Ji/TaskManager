package com.example.taskmanager.config;

import com.example.taskmanager.model.TaskPriority;
import com.example.taskmanager.repository.PriorityRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriorityDataInitializer {

    @Autowired
    private PriorityRepository priorityRepository;

    @PostConstruct
    public void init() {
        if (priorityRepository.count() == 0) {
            priorityRepository.save(new TaskPriority(null, "Nízká"));
            priorityRepository.save(new TaskPriority(null, "Střední"));
            priorityRepository.save(new TaskPriority(null, "Vysoká"));
        }
    }
}
