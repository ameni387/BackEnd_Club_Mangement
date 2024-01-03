package edu.polytech.securityjwt_polytech.Repositories;

import edu.polytech.securityjwt_polytech.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoleRepository extends JpaRepository<AppRole, Long> {

    public AppRole findByRoleName(String roleName);

}
