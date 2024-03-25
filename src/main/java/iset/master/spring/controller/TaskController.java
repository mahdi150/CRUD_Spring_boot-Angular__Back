package iset.master.spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional ;
import jakarta.persistence.EntityNotFoundException;
import iset.master.spring.DTO.CountType;
import iset.master.spring.model.Task;
import iset.master.spring.repository.TaskRepository;
import iset.master.spring.service.TaskService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api")
public class TaskController {
	
	private TaskService taskService ;
	@GetMapping("/task")
	public List<Task> getTasks() {
		return taskService.getTasks();
	}
	
	@GetMapping("/task/{id}")
	public Optional getTask(@PathVariable Long id) {
	    return taskService.getTaskById(id);
	}
	
	@PostMapping("/task")
	public Task addTask(@RequestBody Task task) {
		return taskService.save(task);
	}
	
	 @PutMapping("/task/{id}")
	    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable Long id) {
		 Optional<Task> optionalTask = taskService.getTaskById(id);
	        if (optionalTask.isPresent()) {
	            Task existingTask = optionalTask.get();

	            // Update properties of the existing task with values from the provided task
	            existingTask.setTitle(task.getTitle());
	            existingTask.setType(task.getType());
	            existingTask.setDescription(task.getDescription());
	            existingTask.setDueDate(task.getDueDate());

	            // Save the updated task
	            taskService.save(existingTask);

	            return ResponseEntity.ok().body("Task updated successfully.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with ID: " + id);
	        }
	    }
	 
	 @DeleteMapping("/task/{id}")
	 public ResponseEntity<?> deleteTask(@PathVariable Long id) {
	     Optional<Task> taskOptional = taskService.getTaskById(id);
	     if (taskOptional.isPresent()) {
	         taskService.deleteTask(id);
	         return ResponseEntity.ok().body("Task deleted successfully.");
	     } else {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with ID: " + id);
	     }
	 }

	 @DeleteMapping("/task")
	 public void deleteAllTasks() {
		 taskService.deleteAll();
	 }
	 
	 @GetMapping("/task/data/percentCountType")
	 public  List<CountType> getPercentageGroupByType(){
		return  taskService.getPercentageGroupByType();
	 }
	 
	 @GetMapping("/task/search")
	    public List<Task> searchTasksByTitleContaining(@RequestParam String letter) {
	        return taskService.searchTasksWithTitleContaining(letter);
	    }
	 

}
