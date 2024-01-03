package edu.polytech.securityjwt_polytech.Models;

import edu.polytech.securityjwt_polytech.entities.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentModel {

    private Integer NSC;
    private String Email;
    private  Integer ClassroomId;
    private List<ClubModel> clubs;
    //private Integer ClubId;


}
