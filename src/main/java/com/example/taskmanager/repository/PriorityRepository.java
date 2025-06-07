package com.example.taskmanager.repository;

import com.example.taskmanager.model.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<TaskPriority, Long> {
}
