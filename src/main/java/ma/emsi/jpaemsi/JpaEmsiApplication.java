package ma.emsi.jpaemsi;

import ma.emsi.jpaemsi.entities.Role;
import ma.emsi.jpaemsi.entities.User;
import ma.emsi.jpaemsi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaEmsiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaEmsiApplication.class, args);
    }
    @Bean//s'execute au demarrage
CommandLineRunner start(UserService userService){
        return args -> {
            //ajouter les utilisateurs
            User u=new User();
            u.setUserName("user1");
            u.setPassword("123456");
            userService.addNewUser(u);

            User u2=new User();
            u2.setUserName("admin");
            u2.setPassword("123456");
            userService.addNewUser(u2);

            //ajouter les roles

            Stream.of("STUDENT","USER","ADMIN").forEach(r->{
                Role role1=new Role();
                role1.setRoleName(r);
                userService.addNewRole(role1);
            });

            //affecter les roles aux utilisateurs

            userService.addRoleToUser("user1","STUDENT");
            userService.addRoleToUser("user1","USER");
            userService.addRoleToUser("admin","USER");
            userService.addRoleToUser("admin","ADMIN");


            try{
                User user=userService.authenticate("user1","123456");
                System.out.println(user.getUserId());
                System.out.println(user.getUserName());
                System.out.println("Roles=>");
                user.getRoles().forEach(r->{
                    System.out.println("Role=>"+r.toString());
                });

            }
            catch (Exception exception){
                exception.printStackTrace();

            }

        };
}
}
