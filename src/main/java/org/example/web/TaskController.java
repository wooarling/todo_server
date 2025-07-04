package org.example.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.persist.TaskStatus;
import org.example.service.TaskService;
import org.example.web.vo.ResultResponse;
import org.example.web.vo.TaskStatusRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/* 새로운 할 일 추가
@param req 추가하고자 하는 할 일
@return 추가된 할 일*/

@Slf4j
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor



public class TaskController  {

    private final TaskService taskService;
    @PostMapping
    public ResponseEntity<MyTask> createTask(@RequestBody TaskRequest req) {
        var result= this.taskService.add(req.getTitle(), req.getDescription(), req.getDueDate());
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<MyTask>> getTasks(Optional<String> dueDate) {
        List<MyTask> result;
        if (dueDate.isPresent()) {
            result = this.taskService.getByDueDate(dueDate.get());
        }
        else{
            result=this.taskService.getAll();
            }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyTask> fetchOneTask(@PathVariable Long id) {

       var result=this.taskService.getOne(id);
        return ResponseEntity.ok(result);

    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<MyTask>> getByStatus(@PathVariable TaskStatus status) {
        var result=this.taskService.getByStatus(status);
        return ResponseEntity.ok(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MyTask> updateTask(@PathVariable Long id,
                                             @RequestBody TaskRequest task) {
        var result=this.taskService.update(id, task.getTitle(),
                task.getDescription(), task.getDueDate());
        return ResponseEntity.ok(result);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<MyTask> updateTaskStatus(@PathVariable Long id,
                                                   @RequestBody TaskStatusRequest req) {
        var result=this.taskService.updateStatus(id, req.getStatus());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deleteTask(@PathVariable Long id) {
        var result=this.taskService.delete(id);
        return ResponseEntity.ok(new ResultResponse(result));
    }

    @GetMapping("/status")
    public ResponseEntity<TaskStatus[]> getAllTaskStatus() {
        var status=TaskStatus.values();
        return ResponseEntity.ok(status);
    }

}
