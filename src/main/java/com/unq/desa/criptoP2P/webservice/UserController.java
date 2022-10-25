package com.unq.desa.criptoP2P.webservice;

import com.unq.desa.criptoP2P.model.dto.UserDto;

import com.unq.desa.criptoP2P.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiResponses(value={
    @ApiResponse(code=200, message = "OK"),
    @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/userAll")
    public List<UserDto> index() {
        return userService.get();
    }
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/user")
    public UserDto show(@RequestParam("userId") Integer id) {
        return userService.getById(id);
    }
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})

    @PostMapping(value = "/register")
    public void register(@Valid @RequestBody UserDto user, Errors errors) throws Exception {
        this.userService.updateUser(user);
    }

    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PutMapping(value = "/user")
    public void update(@RequestBody UserDto user, Errors errors) throws Exception{
        this.userService.updateUser(user);
    }
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @DeleteMapping(value = "/user")
    public void delete(@PathVariable Integer id) {
        this.userService.delete(id);
    }

}
