package com.example.taskmanager.repository;

import com.example.taskmanager.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Long> {}
