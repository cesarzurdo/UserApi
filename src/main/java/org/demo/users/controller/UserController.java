package org.demo.users.controller;

import org.demo.users.request.UserRequest;
import org.demo.users.response.UserResponse;
import org.demo.users.security.AuthCredentials;
import org.demo.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping(path = "/sign-up")//Registrarse
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserResponse> getUser(@Valid @RequestBody AuthCredentials credentials){
        return ResponseEntity.ok(userService.findUserByCredentials(credentials));
    }
//    @GetMapping(path = "/login")
//    public ResponseEntity<UserResponse> getUser(@RequestHeader(value = "Authorization", required = true) String token){
//        return ResponseEntity.ok(userService.findUserByToken(token));
//    }

}
