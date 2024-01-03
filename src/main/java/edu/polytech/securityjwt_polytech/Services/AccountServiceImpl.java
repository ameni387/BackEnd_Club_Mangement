package edu.polytech.securityjwt_polytech.Services;

import edu.polytech.securityjwt_polytech.Repositories.RoleRepository;
import edu.polytech.securityjwt_polytech.Repositories.UserRepository;
import edu.polytech.securityjwt_polytech.entities.AppRole;
import edu.polytech.securityjwt_polytech.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional //les methodes sont transactionnelles càd dès qu'il fait commit, il sait qu'on a ajouté un role à l'utilisateur et il l'a ajoute à la BD ds la table d'association
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; //on va l'utiliser pour cryter le mdp de user lors de l'ajout de ce user ds la BD
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public AppUser saveUser(AppUser user) {
        String hashPW=bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
    AppRole role= roleRepository.findByRoleName(roleName);
    AppUser user=userRepository.findByUsername(username);
    user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUserName(String username) {
        return userRepository.findByUsername((username));
    }

    @Override
    public List<AppUser> listUsers() {
        return null;
    }


}
