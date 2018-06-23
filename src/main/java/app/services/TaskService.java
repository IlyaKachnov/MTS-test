package app.services;

import app.models.Task;
import app.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public void save(Task task) {

        taskRepository.save(task);
    }

    public Optional<Task> findById(UUID id) {

        return taskRepository.findById(id);
    }

    @Async
    public void updateTask(UUID id) {
        Optional<Task> optional = taskRepository.findById(id);
        if (!optional.isPresent()) {
            return;
        }

        Task task = optional.get();
        task.setStatus("running");
        task.setTimestamp(new Date());
        taskRepository.save(task);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        task.setStatus("finished");
        task.setTimestamp(new Date());
        taskRepository.save(task);
    }
}
