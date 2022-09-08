package webservice;

import model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> index() {
        return userService.get();
    }

    @GetMapping
    public User show(Integer id) {
        return userService.getById(id);
    }

    @PostMapping(value = "/user/create")
    public void save(@RequestBody User user) throws Exception {
       this.userService.createUser(user);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        this.userService.save(user);
    }

    @DeleteMapping
    public void delete(@PathVariable Integer id) {
        this.userService.delete(id);
    }

}
