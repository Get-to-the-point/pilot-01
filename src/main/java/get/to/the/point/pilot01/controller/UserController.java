package get.to.the.point.pilot01.controller;

import get.to.the.point.pilot01.Service.UserService;
import get.to.the.point.pilot01.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // /user/1, /user/2 형식으로 사용자 정보를 조회
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id) {
        return this.userService.getUser(id);
    }

    @PostMapping("/user")
    public Boolean createUser(User user) {
        return userService.createUser(user);
    }

    @PutMapping("/user/{id}")
    public Boolean updateUser(UserUpdateDto user) {
        return userService.updateUser(user);
    }
}
