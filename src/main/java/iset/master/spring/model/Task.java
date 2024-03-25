package iset.master.spring.model;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
	
	@Id
	@GeneratedValue
	private Long id ;
	@Column(length = 50) 
	private String title ;
	private String type ;
	private String description ;
	private Date dueDate ;
}
