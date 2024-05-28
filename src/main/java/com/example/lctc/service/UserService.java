package com.example.lctc.service;

import com.example.lctc.dto.UserDTO;
import com.example.lctc.entity.User;

public interface UserService {
    UserDTO getUserDetails(Long userId);
    User addUser(UserDTO userDTO);
    User login(UserDTO userDTO);
    User participate(UserDTO userDTO);
}
