package edu.polytech.securityjwt_polytech.entities;





import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id ;
    private  String Name;



    @OneToMany(mappedBy="classroom",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("classroom")
    private List<Student> students;


    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}