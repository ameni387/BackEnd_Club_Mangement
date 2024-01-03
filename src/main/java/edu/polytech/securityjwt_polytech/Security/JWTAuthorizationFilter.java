package edu.polytech.securityjwt_polytech.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public class JWTAuthorizationFilter extends OncePerRequestFilter { //OncePerRequestFilter <=> s'execute pour chaque requete
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
        System.out.println("jwt: " + jwt);
        //si le authorization est diff a null si null c a d j'ai n'et pas un token (visa) & et s'il est existe il devrais commancer par Bearer
        //pour la verification de jwt
        if (jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            System.out.println("Attempting to parse JWT...");

            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();
            //aprés la virefication on recupere le user

            if (claims != null) {
                String username = claims.getSubject();
                System.out.println("Username from token: " + username);



                ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles"); //on a utilisé le Map car les roles sont ds un tableau clé/valeur("Authority":"ADMIN")
                Collection<GrantedAuthority> authorities = new ArrayList<>(); //pour charger le context, springSecurity a besoin des roles sous forme d'une collection de type GrantedAutority

                if (roles != null) {
                    roles.forEach(r -> {
                        authorities.add(new SimpleGrantedAuthority(r.get("authority")));
                    });

                } else {
                    System.out.println("Roles claim is null or not present in the token.");
                }

// authentifier l'utilisateur
                //password null car j'ai pas le besoin car on peut pas mettre le paswd dans jwt
                UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(username, null, authorities);
                //il faut ithentifier l'utilisateur pour que je peu le dire tu peu passer ou non
                SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
                System.out.println("JWT successfully parsed and user authenticated.");
            } else {
                System.out.println("Claims is null. Token parsing failed.");
            }
        } catch (Exception e) {
            System.out.println("Error parsing JWT: " + e.getMessage());
            e.printStackTrace(); // Log the full stack trace for debugging purposes.
        }

        filterChain.doFilter(request, response);
    }


    protected void doFilterInternal_prev(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(SecurityConstants.HEADER_STRING); //recuperer le header de la requete nommé "SecurityConstants.HEADER_STRING" <=>recuperer le token
        System.out.println("jwt: "+ jwt);
        if (jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {  //si le token est null ou ne commence pas par le prefixe ce n'est pas lapeine de calculer la signature
            filterChain.doFilter(request, response); // doFilter càd passe au filtre svt et après springSecurity va decedé ce qu'il va faire càd je quitte
            return; //je quitte la méthode
        }

        //on passe mtn à signer le token
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET) //signer le token avec le secret
                .parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, "")) //remplace le bearer par le vide: c'est une façon pour supprimer le prefixe
                .getBody(); // recuperer le contenu de token
        String username = claims.getSubject(); //recuperer ces donnees(username et roles pour les donner à spring)
        ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles"); //on a utilisé le Map car les roles sont ds un tableau clé/valeur("Authority":"ADMIN")
        Collection<GrantedAuthority> authorities = new ArrayList<>(); //pour charger le context, springSecurity a besoin des roles sous forme d'une collection de type GrantedAutority
        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.get("authority")));
        }); //la clé s'appelle authority

        UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(username, null, authorities); //on a mis le mdp=null car dejà elle n'existe pas ds le token et ce n'est pa lapeine de le chercher car on a plus besoin et avec le jwt qu'on a reçs, l'utilisateur est supposé authentifié, on a besoin just de verifier si le token est valide ou pas
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);//charger le context le springSecurity(l'identité de l'utilisateur authentifié)
        filterChain.doFilter(request, response);
    }
}
