package com.example.lctc.controller;

import com.example.lctc.dto.UserDTO;
import com.example.lctc.entity.User;
import com.example.lctc.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;
    
    /*
    * API for fetching user details*/
    @GetMapping("/userDetails/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable("userId") Long userId){
        User user = userServiceImpl.getUserDetails(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*
    * Register API
    * Users can register to website*/
    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody UserDTO user){
        User savedUser = userServiceImpl.addUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.OK);
    }

    /*
    * Login API
    * User can login to the website*/
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDTO userDTO) {
        User user = userServiceImpl.login(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*Participate API
    * Users can participate in challenges*/
    @PostMapping("/participate")    
    public ResponseEntity<User> participate(@RequestBody UserDTO userDTO){
        User user = userServiceImpl.participate(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*Challenges API
    * Get all the challenges
    */

//    @GetMapping("/upcomming-challenges")
//    public ResponseEntity<> upcomingChallenges(@RequestBody UserDTO userDTO){
//        User user = userServiceImpl.upcomingChallenges(userDTO);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/past-challenges")
//    public ResponseEntity<> pastChallenges(@ResponseBody UserDTO userDTO){
//        User user = userServiceImpl.pastChallenges(userDTO);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    // API for Internal Usage
//
//    /*
//
//     */
//    @GetMapping("/challenges")
//    public ResponseEntity<> challenges(@RequestBody UserDTO userDTO){
//        User user = userServiceImpl.challenges(userDTO);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
