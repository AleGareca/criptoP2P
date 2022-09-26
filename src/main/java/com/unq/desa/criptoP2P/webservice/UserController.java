package com.unq.desa.criptoP2P.webservice;

import com.unq.desa.criptoP2P.model.user.User;

import com.unq.desa.criptoP2P.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiResponses(value={
    @ApiResponse(code=200, message = "OK"),
    @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/userAll")
    public List<User> index() {
        return userService.get();
    }
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/user")
    public User show(@RequestParam("userId") Integer id) {
        return userService.getById(id);
    }
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/user")
    public void save(@RequestBody User user) throws Exception {
       this.userService.createUser(user);
    }

    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PutMapping(value = "/user")
    public void update(@RequestBody User user) {
        this.userService.save(user);
    }
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @DeleteMapping(value = "/user")
    public void delete(@PathVariable Integer id) {
        this.userService.delete(id);
    }

}
