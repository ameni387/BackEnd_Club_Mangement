package edu.polytech.securityjwt_polytech.Services;

import edu.polytech.securityjwt_polytech.entities.Classroom;

import java.util.List;

public interface ClassroomService {
    // save operation
    Classroom saveClassroom (Classroom classroom);

    // read operation
    List<Classroom> fetchClassroomList();

    // update operation
    Classroom updateClassroom(Classroom classroom, Integer classroomId);

    // delete operation
    void deleteClassroomById(Integer classroomId);
    //get by id operation
    Classroom getClassroomById(Integer classroomId);
}
