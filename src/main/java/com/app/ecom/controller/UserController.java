package com.app.ecom.controller;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.User;
import com.app.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
//    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
//    public ResponseEntity<List<User>> getAllUsers(){
        public ResponseEntity<List<UserResponse>> getAllUsers(){
            return new ResponseEntity<>(userService.fetchAllUsers(),
                HttpStatus.OK);
        //return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/{id}")
//    public ResponseEntity<User> getUser(@PathVariable Long id){
      public ResponseEntity<UserResponse> getUser(@PathVariable Long id){

            return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
//        public ResponseEntity<String> createUser(@RequestBody User user){
    //        userService.addUser(user);
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
        userService.addUser(userRequest);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
//                                             @RequestBody User updatedUser){
//        boolean updated = userService.updateUser(id, updatedUser);
                                             @RequestBody UserRequest updateUserRequest){
        boolean updated = userService.updateUser(id, updateUserRequest);
        if (updated)
            return ResponseEntity.ok("User updated successfully");
        return ResponseEntity.notFound().build();
    }
}