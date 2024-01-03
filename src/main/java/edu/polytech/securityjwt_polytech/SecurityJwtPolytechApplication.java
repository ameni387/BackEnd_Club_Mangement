package edu.polytech.securityjwt_polytech;

import edu.polytech.securityjwt_polytech.Repositories.TaskRepository;
import edu.polytech.securityjwt_polytech.Services.AccountService;
import edu.polytech.securityjwt_polytech.entities.AppRole;
import edu.polytech.securityjwt_polytech.entities.AppUser;
import edu.polytech.securityjwt_polytech.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class SecurityJwtPolytechApplication {
//    @Autowired
//    private TaskRepository taskRepository;
//    @Autowired
//    private AccountService accountService;
    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtPolytechApplication.class, args);
    }
    @Bean
    //pour qu'il devient un bean spring càd qd cette application demarre, les methodes qui ont l'annotation bean sont exécutées et le resultat retournée devient un beanSpring et là on peut l'injecter par tout
    public BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
/*
    @Bean
    public CommandLineRunner demo(TaskRepository taskRepository, AccountService accountService ) {
        return (args) -> {
            // save a few Users
            accountService.saveUser(new AppUser(null,"admin","1234",null));
            accountService.saveUser(new AppUser(null,"user","1234",null));
            accountService.saveRole(new AppRole(null,"ADMIN"));
            accountService.saveRole(new AppRole(null,"USER"));
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("user","USER");
            Stream.of("T1","T2","T3").forEach(t->{
                taskRepository.save(new Task(null,t));
            });
            taskRepository.findAll().forEach(t->{
                System.out.println(t.getTaskName());
            });
            AppUser as = accountService.findUserByUserName("admin") ;
            System.out.println("AppUser: "+ as.toString());


        };
    }


    @Override
    public void run(String... args) throws Exception {
        accountService.saveUser(new AppUser(null,"admin","1234",null));
        accountService.saveUser(new AppUser(null,"user","1234",null));
        accountService.saveRole(new AppRole(null,"ADMIN"));
        accountService.saveRole(new AppRole(null,"USER"));
        accountService.addRoleToUser("admin","ADMIN");
        accountService.addRoleToUser("admin","USER");
        accountService.addRoleToUser("user","USER");
        Stream.of("T1","T2","T3").forEach(t->{
            taskRepository.save(new Task(null,t));
        });
        taskRepository.findAll().forEach(t->{
            System.out.println(t.getTaskName());
        });
    }*/
}
