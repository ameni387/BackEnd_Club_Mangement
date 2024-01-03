package edu.polytech.securityjwt_polytech.Repositories;

import edu.polytech.securityjwt_polytech.entities.Student;
import edu.polytech.securityjwt_polytech.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT COUNT(*) FROM Student s")
    long getTotalStudents();

    // Query 2: Nombre d’étudiant participants dans des clubs
    @Query("SELECT COUNT(s) FROM Student s WHERE SIZE(s.clubs) > 0")
    long getStudentsInClubs();

    // Query 3: Meilleur Etudiant (celui qui participe le plus au club)
    @Query("SELECT s FROM Student s JOIN s.clubs c GROUP BY s ORDER BY COUNT(c) DESC")

    //@Query("SELECT s FROM Student s ORDER BY SIZE(s.clubs) DESC")
    List<Student> getBestStudent();
    //Meilleur Classe (admettant un nombre max de participants aux clubs) :
    @Query("SELECT s.classroom, COUNT(c) AS clubCount " +
            "FROM Student s JOIN s.clubs c " +
            "GROUP BY s.classroom " +
            "ORDER BY clubCount DESC")
    List<Object[]> findBestClassWithMaxParticipants();

//Nombre d'étudiants participant à des clubs par classe :
    @Query("SELECT s.classroom, COUNT(c) " +
            "FROM Student s JOIN s.clubs c " +
            "GROUP BY s.classroom")
    List<Object[]> findNumberOfStudentsParticipatingInClubsByClass();

    //Liste des étudiants participant à des clubs (par classe) :

    @Query("SELECT s.classroom " +
            "FROM Student s JOIN s.clubs c " +
            "GROUP BY s.classroom")
    List<Object[]> findStudentsParticipatingInClubsByClass();
}



