package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.persist.TaskEntity;
import org.example.persist.TaskRepository;
import org.example.persist.TaskStatus;
import org.example.web.MyTask;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    public MyTask add(String title, String description, LocalDate dueDate) {
        var e= TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(Date.valueOf(dueDate))
                .status(TaskStatus.TODO)
                .build();
         var saved= this.taskRepository.save(e);
         return entityToObject(saved);
    }
    private MyTask entityToObject(TaskEntity e) {
        return MyTask.builder()
                .id(e.getId())
                .title(e.getTitle())
                .description(e.getDescription())
                .status(e.getStatus())
                .dueDate(e.getDueDate().toString())
                .createdAt(e.getCreatedAt().toLocalDateTime())
                .updatedAt(e.getUpdatedAt().toLocalDateTime())
                .build();

    }
}
