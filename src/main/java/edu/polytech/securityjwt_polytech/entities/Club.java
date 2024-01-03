package edu.polytech.securityjwt_polytech.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer REF;

    private Date Creation_date;

    @ManyToMany(mappedBy = "clubs",fetch = FetchType.LAZY)
    @JsonIgnore
    List<Student> students;


    /*public List<Student> getStudents() {
        return students;
    }
    public void addStudent(Student student) {
        this.students.add(student);
        student.getClubs().add(this);
    }*/
    public void setStudents(List<Student> students) {
        this.students =students;
    }

    @Override
    public String toString() {
        return "Club{" +
                "REF=" + REF +
                ", Creation_date=" + Creation_date +
                ", students=" + students +
                '}';
    }
}

