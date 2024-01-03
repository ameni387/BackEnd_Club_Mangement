package edu.polytech.securityjwt_polytech.Services;


import edu.polytech.securityjwt_polytech.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
@Qualifier("ImplToUse")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user=accountService.findUserByUserName(username);
        if(user==null) throw new UsernameNotFoundException(username);

        Collection<GrantedAuthority> authorities=new ArrayList<>();
        user.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        return new User(user.getUsername(), user.getPassword(), authorities); //on doit retourner un user de spring qui contien le nom le mdp et les role sous la forme d'une collection de type GrantedAuthority

    }
   /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  //UserDetails:objet d'une classe qui implemente l'interface userDetail
        AppUser user=accountService.findUserByUserName(username);
        if(user==null) throw new UsernameNotFoundException(username);

        Collection<GrantedAuthority> authorities=new ArrayList<>();
        user.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        return new User(user.getUsername(), user.getPassword(), authorities); //on doit retourner un user de spring qui contien le nom le mdp et les role sous la forme d'une collection de type GrantedAuthority
    }*/
}
