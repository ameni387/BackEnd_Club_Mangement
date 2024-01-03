package edu.polytech.securityjwt_polytech.Services;


import edu.polytech.securityjwt_polytech.entities.AppRole;
import edu.polytech.securityjwt_polytech.entities.AppUser;

import java.util.List;


//interface qui permet de centraliser la gestion des utilisateurs et des roles
public interface AccountService {
    //definir les méthodes qu'on a besoin ds l'application
    public AppUser saveUser(AppUser user);  //ajouter un utilisateur
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String username, String roleName);//associer un role à un utilisateur
    public AppUser findUserByUserName(String username);//retourner l'utilisateur
    List<AppUser> listUsers();
}
