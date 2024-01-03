package edu.polytech.securityjwt_polytech.Controlers;

import edu.polytech.securityjwt_polytech.Repositories.ClassroomRepository;
import edu.polytech.securityjwt_polytech.Repositories.StudentRepository;
import edu.polytech.securityjwt_polytech.Services.ClassroomService;
import edu.polytech.securityjwt_polytech.entities.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(value="*")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/classrooms/{studentId}")
    public Classroom saveClassroom(@PathVariable Integer studentId, @RequestBody Classroom classroom) {

        return classroomService.saveClassroom(classroom);
    }

    @GetMapping("/Allclassrooms")

    public List<Classroom> fetchClassroomList() {
        return classroomService.fetchClassroomList();
    }

    @DeleteMapping("/classroom/{id}")
    public String deleteClassroomById(@PathVariable("id")
                                      Integer classroomId) {
        try {
            classroomService.deleteClassroomById(classroomId);
            return String.valueOf(new ResponseEntity<>(HttpStatus.NO_CONTENT));
            //return "Deleted Successfully";
        } catch (Exception e) {
            return String.valueOf(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/updateClassroom/{id}")
    public ResponseEntity<Classroom> updateClassroom( @RequestBody Classroom classroom,@PathVariable Integer classroomId) {
        Classroom updatedClassroom = classroomService.updateClassroom(classroom,classroomId);
        return ResponseEntity.ok(updatedClassroom);
    }



    //JPQL queries
    /*@GetMapping("/students-in-clubs-per-class")
    List<Object[]> countStudentsInClubsPerClassroom(){
        return classroomRepository.countStudentsInClubsPerClassroom();
    };*/
    @GetMapping("/best-class")
    public List<Object[]> getBestClassWithMaxParticipants() {
        return studentRepository.findBestClassWithMaxParticipants();
    }

    @GetMapping("/students-participating-count")
    public List<Object[]> getNumberOfStudentsParticipatingInClubsByClass() {
        return studentRepository.findNumberOfStudentsParticipatingInClubsByClass();
    }
    @GetMapping("/Students-Participating-In-Clubs-By-Class")
    public List<Object[]> findStudentsParticipatingInClubsByClass() {
        return studentRepository.findStudentsParticipatingInClubsByClass();
    }

}

