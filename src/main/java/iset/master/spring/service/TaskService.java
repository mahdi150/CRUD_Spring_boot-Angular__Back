package iset.master.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional ;
import org.springframework.stereotype.Service;

import iset.master.spring.DTO.CountType;
import iset.master.spring.model.Task;
import iset.master.spring.repository.TaskRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {
	
	private TaskRepository taskRepository ;

	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		return taskRepository.getAllTaskByDueDate();
	}

	public Optional<Task> getTaskById(Long id) {
		// TODO Auto-generated method stub
		return taskRepository.findById(id);
	}

	public Task save(Task task) {
		// TODO Auto-generated method stub
		return taskRepository.saveAndFlush(task);
	}

	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return taskRepository.existsById(id);
	}

	public void deleteTask(Long id) {
		taskRepository.deleteById(id);	
	}

	public void deleteAll() {
		
		taskRepository.deleteAll();
		
	}
	
	public List<CountType> getPercentageGroupByType(){
		return taskRepository.getPercentageGroupByType();
	}

	public List<Task> searchTasksWithTitleContaining(String letter) {
        return taskRepository.findByTitleContaining(letter);
    }
}
