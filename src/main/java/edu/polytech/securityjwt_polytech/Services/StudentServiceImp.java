package edu.polytech.securityjwt_polytech.Services;

import edu.polytech.securityjwt_polytech.Repositories.StudentRepository;
import edu.polytech.securityjwt_polytech.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void saveStudent(Student student) {
         studentRepository.save(student);


    }
    /*@Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);


    }*/

    @Override
    public List<Student> fetchStudentList() {
        return studentRepository.findAll();

    }
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

   /* @Override
    public List<Student> getStudentsByClubRef(Long clubRef) {
        return studentRepository.findByClubsRef(clubRef);
    }*/

    @Override
    public Student updateStudent(Student student, Integer studentId) {
        Student existingStudent = getStudentById(studentId);
        existingStudent.setEmail(student.getEmail());
        existingStudent.setClassroom(student.getClassroom());
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}
