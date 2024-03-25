package iset.master.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import iset.master.spring.DTO.CountType;
import iset.master.spring.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
	
	//NATIVEQUERRY
	@Query(value="select * from task order by due_date desc",nativeQuery= true)
	public List<Task> getAllTaskByDueDate();

	List<Task> findByTitleContaining(String keyword);
	
	
	
	//JPAQUERY
	@Query(value = "SELECT new iset.master.spring.DTO.CountType(COUNT(*) / (SELECT COUNT(*) FROM Task) * 100, type) FROM Task GROUP BY type")
	public List<CountType> getPercentageGroupByType();

}
