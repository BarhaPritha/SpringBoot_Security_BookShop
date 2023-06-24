package com.bjit.SpringProject.controllers;


import com.bjit.SpringProject.model.AuthenticationRequest;
import com.bjit.SpringProject.model.AuthenticationResponse;
import com.bjit.SpringProject.model.UserRequestModel;
import com.bjit.SpringProject.service.UserService;
import com.bjit.SpringProject.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        return new ResponseEntity<>(authenticationService.login(authenticationRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRequestModel requestModel){
        return userService.register(requestModel);
    }

    @GetMapping("/getUser")
    public ResponseEntity<Object> getUser(@RequestBody String email){
        return userService.getUser(email);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<Iterable<Object>> allUsers(){
        return new ResponseEntity<Iterable<Object>>(Collections.singleton(userService.getAllUsers()), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserRequestModel user) {
        return userService.updateUser(user);
    }

}
