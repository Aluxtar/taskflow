package com.taskflow.taskflow.service;

import com.taskflow.taskflow.model.Task;
import com.taskflow.taskflow.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //Create new tasks
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    //Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    //Show task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    //Update task
    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(task);
        }).orElseGet(() -> {
            // Create task if the id does not exist
            updatedTask.setId(id);
            return taskRepository.save(updatedTask);
        });
    }

    //Delete task by ID
    public boolean deleteTask(Long id) {
        if(taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

