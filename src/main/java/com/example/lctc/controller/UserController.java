package com.example.lctc.controller;

import com.example.lctc.dto.UserDTO;
import com.example.lctc.entity.Challenge;
import com.example.lctc.entity.User;
import com.example.lctc.exception.GeneralException;
import com.example.lctc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    
    /*
    * API for fetching user details*/
    @GetMapping("/userDetails/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable("userId") Long userId){
        User user = userService.getUserDetails(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*
    * Register API
    * Users can register to website*/
    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody UserDTO user){
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.OK);
    }

    /*
    * Login API
    * User can login to the website*/
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDTO userDTO) {
        User user = userService.login(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*Participate API
    * Users can participate in challenges*/
    @PostMapping("/participate")    
    public ResponseEntity<User> participate(@RequestBody UserDTO userDTO){
        User user = userService.participate(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*Challenges API
    * Get all the challenges
    */

    @GetMapping("/upcomming-challenges")
    public ResponseEntity<> upcomingChallenges(@RequestBody UserDTO userDTO){
        User user = userService.upcomingChallenges(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/past-challenges")
    public ResponseEntity<> pastChallenges(@ResponseBody UserDTO userDTO){
        User user = userService.pastChallenges(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API for Internal Usage

    /*

     */
    @GetMapping("/challenges")
    public ResponseEntity<> challenges(@RequestBody UserDTO userDTO){
        User user = userService.challenges(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<> 
}
