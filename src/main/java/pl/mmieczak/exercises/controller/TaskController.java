package pl.mmieczak.exercises.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mmieczak.exercises.model.Task;
import pl.mmieczak.exercises.model.TaskType;
import pl.mmieczak.exercises.service.TaskService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    String showAll(Model model) {
        List<Task> taskList = taskService.findAll();
        model.addAttribute("tasks", taskList);
        model.addAttribute("task", new Task());
        return "index";
    }

    @GetMapping("/create")
    String createTask(Model model) {
        model.addAttribute("new_task", new Task());
        return "create";
    }

    @GetMapping("/task")
    String editTask(@RequestParam Long id, Model model) {
        Optional<Task> taskOptional = taskService.findOneById(id);
        if (taskOptional.isPresent()) {
            model.addAttribute("edit_task", taskOptional.get());
            return "task";
        }
        return "redirect:/";
    }

    @PostMapping("/task/add")
    String addTask(Task task) {
        task.setRegistrationDateTime(Instant.now());
        task.setStatus(TaskType.ONGOING);
        taskService.save(task);
        return "redirect:/";
    }

    @PostMapping("/task/update")
    String updateTask(Task task) {
        taskService.update(task);
        return "redirect:/";
    }

    @GetMapping("/delete")
    String deleteTask(@RequestParam Long id, Task task) {
        taskService.delete(task);
        return "redirect:/";
    }

    @GetMapping("/ongoing")
    String showOngoingTasks(Model model) {
        List<Task> taskList = taskService.findOngoing();
        model.addAttribute("tasks", taskList);
        model.addAttribute("task", new Task());
        return "index";
    }

    @GetMapping("/archived")
    String showArchivedTasks(Model model) {
        List<Task> taskList = taskService.findArchived();
        model.addAttribute("tasks", taskList);
        model.addAttribute("task", new Task());
        return "index";
    }

}
