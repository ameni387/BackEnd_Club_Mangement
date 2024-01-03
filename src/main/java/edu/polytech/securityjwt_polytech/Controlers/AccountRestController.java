package edu.polytech.securityjwt_polytech.Controlers;

import edu.polytech.securityjwt_polytech.Services.AccountService;
import edu.polytech.securityjwt_polytech.entities.AppRole;
import edu.polytech.securityjwt_polytech.entities.AppUser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.AlgorithmConstraints;
import java.util.List;

@RestController
public class AccountRestController {
    @Autowired
    private AccountService accountService;
    @GetMapping(path="/users")
    //@PostAuthorize("hasAuthority('USER')")
    public List<AppUser> appUsers(){
        return accountService.listUsers();
    }
    @PostMapping(path="/add")
    public  AppUser saveUser(@RequestBody AppUser appUser){
        return accountService.saveUser(appUser);
    }
    @PostMapping(path = "/roles")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return  accountService.saveRole(appRole);
    }
    @PostMapping(path = "addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUserName(),roleUserForm.getUserRole());
    }
    /*@GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        String authToken=request.getHeader("Authorization");
        if(authToken!=null && authToken.startsWith("Bearer")){
            String refreshToken=authToken.substring(7);
            Algorithm algorithm= AlgorithmConstraints.HMAC256("mySecret2");
            JWTVerifier jwtVerifier= JWT.require(algorithm).build();
            DecodedJWT decodedJWT=jwtVerifier.verify((refreshToken));
            String username=decodedJWT.getSubject();
        }
    }*/
}
@Data
class  RoleUserForm{
    private String UserName;
    private String UserRole;
}
