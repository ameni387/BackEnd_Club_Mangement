package edu.polytech.securityjwt_polytech.Repositories;

import edu.polytech.securityjwt_polytech.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {
}
