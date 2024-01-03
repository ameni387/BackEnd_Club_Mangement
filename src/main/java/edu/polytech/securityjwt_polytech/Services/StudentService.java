package edu.polytech.securityjwt_polytech.Services;

import edu.polytech.securityjwt_polytech.entities.Student;

import java.util.List;

public interface StudentService {
    void saveStudent (Student student);

    // read operation
    List<Student> fetchStudentList();

    // update operation
    Student updateStudent(Student student, Integer studentId);

    // delete operation
    void deleteStudentById(Integer studentId);
    Student getStudentById(Integer studentId);
    // List<Student> getStudentsByClubRef(Long clubRef);
}
