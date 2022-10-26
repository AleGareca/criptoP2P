package com.unq.desa.criptoP2P.webservice;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.dto.UserDto;

import com.unq.desa.criptoP2P.service.UserService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Return all users (internal use)")
    @ApiResponses(value={
    @ApiResponse(code=200, message = "OK"),
    @ApiResponse(code=400,message = "Bad Request")})
    @GetMapping("/userAll")
    public List<UserDto> index() {
        return userService.get();
    }
    @Operation(summary = "Get a User by its id")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=404,message = "User not found")})
    @GetMapping("/user")
    public UserDto show(@RequestParam("userId") Integer id) {
        return userService.getById(id);
    }
    @Operation(summary = "Register a user in the database")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/register")
    public void register(@Valid @RequestBody UserDto user, Errors errors) throws Exception {
        this.userService.updateUser(user);
    }
    @Operation(summary = "Modify some user data")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PutMapping(value = "/user")
    public void update(@RequestBody UserDto user, Errors errors) throws Exception{
        this.userService.updateUser(user);
    }
    @Operation(summary = "Permanently delete a user")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @DeleteMapping(value = "/user")
    public void delete(@PathVariable Integer id) {
        this.userService.delete(id);
    }

    public void cryptoAssetSaleIntention(Intention intention, @PathVariable Integer userId) {
         this.cryptoAssetSaleIntention(intention,userId);
    }
}
