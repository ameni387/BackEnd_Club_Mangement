package edu.polytech.securityjwt_polytech.Controlers;

import edu.polytech.securityjwt_polytech.Models.ClubModel;
import edu.polytech.securityjwt_polytech.Models.StudentModel;
import edu.polytech.securityjwt_polytech.Repositories.ClubRepository;
import edu.polytech.securityjwt_polytech.Repositories.StudentRepository;
import edu.polytech.securityjwt_polytech.Services.ClassroomService;
import edu.polytech.securityjwt_polytech.Services.ClubService;
import edu.polytech.securityjwt_polytech.Services.StudentService;
import edu.polytech.securityjwt_polytech.entities.Club;
import edu.polytech.securityjwt_polytech.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(value="*")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private ClubService clubService;
    /*@PostMapping("/students")
    void saveStudent(@RequestBody StudentModel student)
    {
        Student s=new Student();

        s.setEmail(student.getEmail());
        s.setClassroom(this.classroomService.getClassroomById(student.getClassroomId()));
        // Fetch existing clubs from the database based on the provided IDs
        List<Club> existingClubs = this.clubService.getClubsByIds(student.getClubs());
        s.setClubs(existingClubs);

        this.studentService.saveStudent(s);
    }*/
    @PostMapping("/students")
    void saveStudent(@RequestBody StudentModel studentModel) {
        Student student = new Student();

        // Set other properties of the student
        student.setEmail(studentModel.getEmail());
        student.setClassroom(this.classroomService.getClassroomById(studentModel.getClassroomId()));

        // Fetch existing clubs from the database based on ClubModel information
        List<Club> existingClubs = new ArrayList<>();
        for (ClubModel clubModel : studentModel.getClubs()) {
            // Assuming ClubModel has an identifier (e.g., REF)
            Club existingClub = clubRepository.findById(clubModel.getRef()).orElse(null);

            if (existingClub != null) {
                existingClubs.add(existingClub);
            } else {
                // Handle the case where the club is not found (optional)
                // You may log a message, throw an exception, or handle it based on your use case.
            }
        }

        // Set the existing clubs for the student
        student.setClubs(existingClubs);

        // Save the student
        this.studentService.saveStudent(student);
    }

    /*@PostMapping("/students")
    public Student saveStudent(@RequestBody Student student)
    {
        return studentService.saveStudent(student);
    }*/


    @GetMapping("/Allstudents")

    public List<Student> fetchStudentList()
    {
        return studentService.fetchStudentList();
    }
    @DeleteMapping("/student/{id}")
    public String deleteStudentById(@PathVariable("id")
                                    Integer studentId) {
        try {
            studentService.deleteStudentById(studentId);
            return String.valueOf(new ResponseEntity<>(HttpStatus.NO_CONTENT));
            //return "Deleted Successfully";
        } catch (Exception e) {
            return String.valueOf(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Student> updateStudent( @RequestBody Student student,@PathVariable Integer id) {
        Student updatedStudent = studentService.updateStudent(student,id);
        return ResponseEntity.ok(updatedStudent);
    }

    //JPQL querys
    @GetMapping("/total")
    public long getTotalStudents() {
        return studentRepository.getTotalStudents();
    }

    @GetMapping("/in-clubs")
    public long getStudentsInClubs() {
        return studentRepository.getStudentsInClubs();
    }

    @GetMapping("/best_student")
    public Student getBestStudent() {
        return studentRepository.getBestStudent().get(1);
    }
   /* @GetMapping("/students/byClubRef/{clubRef}")
    public ResponseEntity<List<Student>> getStudentsByClubRef(@PathVariable Long clubRef) {
        List<Student> studentsInClub = studentService.getStudentsByClubRef(clubRef);
        return ResponseEntity.ok(studentsInClub);
    }*/
}

