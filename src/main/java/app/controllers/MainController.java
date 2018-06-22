package app.controllers;

import app.models.Task;
import app.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MainController {

    private final TaskService taskService;

    @Autowired
    public MainController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public UUID index() {
        return taskService.findAll().stream().findAny().get().getId();
    }

    @PostMapping(value = "/task")
    public ResponseEntity<?> create() {
        Task task = new Task();
        taskService.save(task);
        taskService.updateTask(task.getId());

        return new ResponseEntity<>(task.getId(), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "task/{id}")
    public ResponseEntity<?> show(@PathVariable("id") UUID id) {

//        String uuidStr = id.toString();
//        System.out.println(uuidStr);
//        if (!uuidStr.matches("/^\\{?[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}\\}?$/")) {
//            System.out.println("is not uuid");
//        }

//        if (id instanceof UUID) {
//
//            return new ResponseEntity<>("Not a UUID!", HttpStatus.BAD_REQUEST);
//        }

        Optional<Task> task = taskService.findById(id);

        if (!task.isPresent()) {
            return new ResponseEntity<>("Task not found!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }
}
