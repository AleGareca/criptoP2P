package com.unq.desa.criptoP2P.webservice;

import com.unq.desa.criptoP2P.model.user.User;

import com.unq.desa.criptoP2P.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userAll")
    public List<User> index() {
        return userService.get();
    }

    @GetMapping("/user")
    public User show(@RequestParam("userId") Integer id) {
        return userService.getById(id);
    }

    @PostMapping(value = "/user")
    public void save(@RequestBody User user) throws Exception {
       this.userService.createUser(user);
    }

    @PutMapping(value = "/user")
    public void update(@RequestBody User user) {
        this.userService.save(user);
    }

    @DeleteMapping(value = "/user")
    public void delete(@PathVariable Integer id) {
        this.userService.delete(id);
    }

}
