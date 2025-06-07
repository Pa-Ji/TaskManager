package com.example.taskmanager;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.taskmanager.model.TaskPriority;
import com.example.taskmanager.service.PriorityService;

@Component
public class DataInitializer {

    @Autowired
    private PriorityService priorityService;

    @PostConstruct
    public void init() {
        if(priorityService.findAll().isEmpty()) {
            priorityService.save(new TaskPriority(null, "Nízká"));
            priorityService.save(new TaskPriority(null, "Střední"));
            priorityService.save(new TaskPriority(null, "Vysoká"));
        }
    }
}
