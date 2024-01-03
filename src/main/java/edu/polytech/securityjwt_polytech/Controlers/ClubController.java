package edu.polytech.securityjwt_polytech.Controlers;

import edu.polytech.securityjwt_polytech.Repositories.ClubRepository;
import edu.polytech.securityjwt_polytech.Services.ClubService;
import edu.polytech.securityjwt_polytech.entities.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClubController {
    @Autowired
    private ClubService clubService;
    @Autowired
    private ClubRepository clubRepository;

    @PostMapping("/clubs")
    public Club saveClub(@RequestBody Club club) {
        return clubService.saveClub(club);
    }

    @GetMapping("/Allclubs")

    public List<Club> fetchClubList() {
        return clubService.fetchClubList();
    }

    @DeleteMapping("/club/{id}")
    public String deleteClubById(@PathVariable("id")
                                 Integer clubId) {
        try {
            clubService.deleteClubById(clubId);
            return String.valueOf(new ResponseEntity<>(HttpStatus.NO_CONTENT));
            //return "Deleted Successfully";
        } catch (Exception e) {
            return String.valueOf(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    @GetMapping("/club/{id}")
    public Club getClubById(@PathVariable("id")
                                 Integer clubId) {
        try {
            return clubService.getClubById(clubId);
            //return String.valueOf(new ResponseEntity<>(HttpStatus.NO_CONTENT));
            //return "Deleted Successfully";
        } catch (Exception e) {
            return null;
            //return String.valueOf(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/updateclub/{id}")
    public ResponseEntity<Club> updateClub(@RequestBody Club club, @PathVariable Integer id) {
        Club updatedClub = clubService.updateClub(club, id);
        return ResponseEntity.ok(updatedClub);
    }

    /* @GetMapping("/clubs/byStudentNsc/{studentNsc}")
     public ResponseEntity<List<Club>> getClubsByStudentNsc(@PathVariable Long studentNsc) {
         List<Club> clubsForStudent = clubService.getClubsByStudentNsc(studentNsc);
         return ResponseEntity.ok(clubsForStudent);
     }*/


    //JPQL queries
    @GetMapping("/countTotalClubs")
    public long countTotalClubs() {
        return clubRepository.countTotalClubs();
    }

    @GetMapping("/findBestClub")
    public List<Club> findBestClub() {
        return clubRepository.findBestClub();
    }

    @GetMapping("/findWorstClub")
    public List<Club> findWorstClub() {
        return clubRepository.findWorstClub();
    }

    @GetMapping("/countStudentsInClub/{clubRef}")
    public int countStudentsInClub(@PathVariable Integer clubRef) {
        return clubRepository.countStudentsInClub(clubRef);
    }

    @GetMapping("/countStudentsPerClub")
    public List<Object[]> countStudentsPerClub() {
        return clubRepository.countStudentsPerClub();
    }

    @GetMapping("/findClubsByStudent/{studentNsc}")
    public List<Club> findClubsByStudent(@PathVariable Integer studentNsc) {
        return clubRepository.findClubsByStudent(studentNsc);
    }
}
