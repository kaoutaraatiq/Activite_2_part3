package ma.emsi.jpaemsi.web;

import ma.emsi.jpaemsi.entities.User;
import ma.emsi.jpaemsi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{username}")
    private User user(@PathVariable String username){
        User user=userService.findUserByUserName(username);
        return user;

    }
}
