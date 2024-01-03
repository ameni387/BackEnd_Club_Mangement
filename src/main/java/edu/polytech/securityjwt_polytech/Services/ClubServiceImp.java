package edu.polytech.securityjwt_polytech.Services;

import edu.polytech.securityjwt_polytech.Repositories.ClubRepository;
import edu.polytech.securityjwt_polytech.entities.Classroom;
import edu.polytech.securityjwt_polytech.entities.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class ClubServiceImp implements ClubService  {
    @Autowired
    private ClubRepository clubRepository;

    @Override
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public List<Club> fetchClubList() {
        return clubRepository.findAll();
    }
    public List<Club> getClubsByIds(List<Integer> clubIds) {
        return clubRepository.findAllById(clubIds);
    }

    @Override
    public Club updateClub(Club club, Integer clubId) {
        Club existingClub = getClubById(clubId);
        existingClub.setCreation_date(club.getCreation_date());
        return clubRepository.save(existingClub);
    }

    @Override
    public void deleteClubById(Integer clubId) {
        clubRepository.deleteById(clubId);
    }


    @Override
    public Club getClubById(Integer clubId) {
        return this.clubRepository.findById(clubId).get();

    }

   /* @Override
    public List<Club> getClubsByStudentNsc(Long studentNsc) {
        return clubRepository.findByStudentsNsc(studentNsc);
    }*/
}
