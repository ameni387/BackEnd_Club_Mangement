package edu.polytech.securityjwt_polytech.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Entity
@Data
public class AppUser {
    @Id @GeneratedValue
    private  Long id;
    @Column(unique = true)
    private String username;
    // @JsonIgnore   //lors de la recuperation des données de l'utilisateur, il l'ignore: on le met ds le setter
    private String password;
    @ManyToMany(fetch = FetchType.EAGER) //on a utilisé eager car à chque fois je vais charger un utilisateur, j'ai besion de telecharger ses roles
    private Collection<AppRole> roles = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(Long id, String username, String password, Collection<AppRole> roles) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @JsonIgnore  //je l'ingore
    public String getPassword() {
        return password;
    }
    @JsonSetter  //je le prend
    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<AppRole> roles) {
        this.roles = roles;
    }

}
