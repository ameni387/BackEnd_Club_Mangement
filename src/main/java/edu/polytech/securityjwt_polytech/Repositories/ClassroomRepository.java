package edu.polytech.securityjwt_polytech.Repositories;

import edu.polytech.securityjwt_polytech.entities.Classroom;
import edu.polytech.securityjwt_polytech.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

    /*@Query("SELECT c, COUNT(s) FROM Classroom c JOIN c.students s JOIN s.clubs GROUP BY c")
    List<Object[]> countStudentsInClubsPerClassroom();*/

}
