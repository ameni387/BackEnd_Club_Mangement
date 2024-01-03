package edu.polytech.securityjwt_polytech.Services;

import edu.polytech.securityjwt_polytech.Repositories.ClassroomRepository;
import edu.polytech.securityjwt_polytech.entities.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class ClassroomServiceImp implements ClassroomService{
    @Autowired
    ClassroomRepository classroomRepository;
    @Override
    public Classroom saveClassroom(Classroom classroom) {

        return classroomRepository.save(classroom);
    }

    @Override
    public List<Classroom> fetchClassroomList() {

        return classroomRepository.findAll();
    }
    @Override
    public  Classroom getClassroomById(Integer classroomId) {
        return this.classroomRepository.findById(classroomId).get();

    }

    @Override
    public Classroom updateClassroom(Classroom classroom, Integer classroomId) {
        Classroom existingClassroom = getClassroomById(classroomId);
        existingClassroom.setName(classroom.getName());
        existingClassroom.setName(classroom.getStudents().toString());
        return classroomRepository.save(existingClassroom);



    }

    @Override
    public void deleteClassroomById(Integer classroomId) {
        classroomRepository.deleteById(classroomId);
    }
}
