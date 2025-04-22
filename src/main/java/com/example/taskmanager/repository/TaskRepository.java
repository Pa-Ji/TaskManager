package com.example.taskmanager.repository;

import com.example.taskmanager.model.TaskModel;
import com.example.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findByUser(User user);

    List<TaskModel> findByUserId(Long userId);

    List<TaskModel> findByPriorityName(String priorityName);
}
