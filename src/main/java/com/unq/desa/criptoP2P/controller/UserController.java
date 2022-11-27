package com.unq.desa.criptoP2P.controller;

import com.unq.desa.criptoP2P.aspect.TimeAnnotation;
import com.unq.desa.criptoP2P.model.dto.ActiveCryptoReportDto;
import com.unq.desa.criptoP2P.model.dto.UserDto;

import com.unq.desa.criptoP2P.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.time.LocalDate;
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
    @GetMapping("/AllUsers")
    @TimeAnnotation
    public List<UserDto> getUsers() {
        return userService.get();
    }
    @Operation(summary = "Get a User  by token")
    @SecurityRequirement(name = "bearer")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=404,message = "User not found")})
    @GetMapping("/user")
    public UserDto viewUserDetail(Authentication authentication) {
        return userService.getByMail(authentication.getName());
    }
    @Operation(summary = "Register a user in the database")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/register")
    public void register(@Valid @RequestBody UserDto user, Errors errors) throws Exception {
        this.userService.registerUser(user);
    }
    @Operation(summary = "Modify some user data")
    @SecurityRequirement(name = "bearer")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PutMapping(value = "/user")
    public void update(Authentication authentication, @RequestBody UserDto user, Errors errors) throws Exception{
        this.userService.updateUser(authentication.getName(),user);
    }
    @Operation(summary = "Permanently delete a user")
    @SecurityRequirement(name = "bearer")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @DeleteMapping(value = "/user")
    public void delete(Authentication authentication) {
        this.userService.delete(authentication.getName());
    }

    @Operation(summary = "Report the traded volume of crypto assets between two dates")
    @SecurityRequirement(name = "bearer")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400,message = "Bad Request")})
    @PostMapping(value = "/report")
    public ActiveCryptoReportDto userReport(Authentication authentication,
                                            @RequestParam("initDate")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate initDate,
                                            @RequestParam("endDate")
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate){
    return userService.generateReport(authentication.getName(), initDate,endDate);
    }
}
