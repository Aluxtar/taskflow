package com.taskflow.taskflow.controller;

import com.taskflow.taskflow.model.Task;
import com.taskflow.taskflow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class TaskViewController {

    @Autowired
    private TaskService taskService;

    // Display all tasks
    @GetMapping("/")
    public String viewAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "index";
    }

    //Show form to create a new task
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "create";
    }

    //Handle form submission for creating a task
    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/";
    }

    //Show form to edit an existing task
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Task> taskOpt = taskService.getTaskById(id);
        if (taskOpt.isPresent()) {
            model.addAttribute("task", taskOpt.get());
            return "edit";
        } else {
            return "redirect:/";
        }
    }

    //Handle form submission for updating a task
    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        taskService.updateTask(id, task);
        return "redirect:/";
    }

    //Handle deletion directly
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}
