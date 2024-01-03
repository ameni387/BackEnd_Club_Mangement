package edu.polytech.securityjwt_polytech.Services;

import edu.polytech.securityjwt_polytech.entities.Club;

import java.util.List;

public interface ClubService {
    Club saveClub (Club club);

    // read operation
    List<Club> fetchClubList();

    // update operation
    Club updateClub(Club club, Integer clubId);

    // delete operation
    void deleteClubById(Integer clubId);
    Club getClubById(Integer clubId);
    public List<Club> getClubsByIds(List<Integer> clubIds);
    //List<Club> getClubsByStudentNsc(Long studentNsc);
}
