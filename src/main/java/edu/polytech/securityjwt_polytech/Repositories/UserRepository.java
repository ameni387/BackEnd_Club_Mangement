package edu.polytech.securityjwt_polytech.Repositories;

import edu.polytech.securityjwt_polytech.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    public AppUser findByUsername(String username);

}
