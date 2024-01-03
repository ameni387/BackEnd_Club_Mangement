package edu.polytech.securityjwt_polytech.Controlers;
import java.util.*;
import edu.polytech.securityjwt_polytech.Repositories.TaskRepository;
import edu.polytech.securityjwt_polytech.entities.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/Alltasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        System.out.println("hello !");
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
}

