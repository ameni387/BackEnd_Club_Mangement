package edu.polytech.securityjwt_polytech.Repositories;

import edu.polytech.securityjwt_polytech.entities.Club;
import edu.polytech.securityjwt_polytech.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface ClubRepository extends JpaRepository<Club, Integer> {
    // Task 1: Nombre Total de clubs
    @Query("SELECT COUNT(c) FROM Club c")
    long countTotalClubs();

    // Task 2: Best Club (ayant le nombre Max d’adhérents)

    @Query("SELECT c FROM Club c JOIN c.students s GROUP BY c ORDER BY COUNT(s) DESC")
    List<Club> findBestClub();
   /* @Query("SELECT c.REF FROM Club c ORDER BY SIZE(c.students) DESC LIMIT 1")
    String findBestClub();*/


    // Task 3: Worst Club (ayant le nombre Min d’adhérents)
    @Query("SELECT c FROM Club c JOIN c.students s GROUP BY c ORDER BY COUNT(s) ASC")
    List<Club> findWorstClub();

    // Task 4: Nombre d’etudiant d’un club donné
    //@Query("SELECT COUNT(c.students) FROM Club c WHERE c.REF = :clubRef")
    @Query("SELECT COUNT(s) FROM Club c JOIN c.students s WHERE c.REF = :clubRef")
    int countStudentsInClub(@Param("clubRef") Integer clubRef);


    // Task 5: Nombre d’étudiant par club
    @Query("SELECT c.REF,COUNT(s) FROM Club c JOIN c.students s GROUP BY c.REF")
    List<Object[]> countStudentsPerClub();

    // Task 6: Liste de Clubs d’un étudiant donné
    @Query("SELECT s.clubs FROM Student s WHERE s.NSC = :studentNsc")
    List<Club> findClubsByStudent(@Param("studentNsc") Integer studentNsc);
}
