package com.example.lctc.service.impl;

import com.example.lctc.dto.UserDTO;
import com.example.lctc.entity.User;
import com.example.lctc.exception.EmptyInputException;
import com.example.lctc.exception.GeneralException;
import com.example.lctc.repository.UserRepository;
import com.example.lctc.service.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO getUserDetails(Long userId){
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            User user = optionalUser.orElse(new User());
            return modelMapper.map(user, UserDTO.class);
        }
        catch (Exception e){
            throw new GeneralException("UserService - getUserDetails");
        }
    }

    public User addUser(UserDTO userDTO) {
        try{
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                    .filterOutAllExcept("userName","userEmail","userPass","isLoggedIn");

            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter("UserFilter",filter);

            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userDTO);

            mappingJacksonValue.setFilters(filterProvider);

            User user = new ModelMapper()
                    .map(mappingJacksonValue.getValue(), new TypeToken<User>() {}.getType());

            return userRepository.save(user);
        }
        catch (Exception e){
            throw new GeneralException("UserService - addUser");
        }
    }

    public User login(UserDTO userDTO){
        try{
            if(userDTO.getUserEmail().isEmpty()){
                throw new EmptyInputException("Please enter email");
            }
            else if(userDTO.getUserPass().isEmpty()){
                throw new EmptyInputException("Please enter password");
            }
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                    .filterOutAllExcept("userName","userEmail","userPass","isLoggedIn");
            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter("UserFilter", filter);
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userDTO);
            mappingJacksonValue.setFilters(filterProvider);
            User user = modelMapper
                    .map(mappingJacksonValue.getValue(), new TypeToken<User>(){}.getType());
        }
        catch (Exception e){
            throw new GeneralException("UserService - login");
        }
        return new User();
    }

    public User participate(UserDTO userDTO) {
        try{
            if(userDTO.isLoggedIn()){

            }
            //check if the user is registered on the website
            //check if the user in logged-in on the website
            //check if the user has already participated in any other contest
            //check if the user has already participated in the current contest
        }
        catch(Exception e){
            throw new GeneralException("UserService - participate");
        }
        return new User();
    }
//
//    public User upcomingChallenges(UserDTO userDTO){
//        // getting all the upcoming challenges
//    }
//
//    public User pastChallenges(UserDTO userDTO){
//        // getting all the past challenges
//    }
//
//    // Internal usage
//
//    public User challenges(UserDTO userDTO){
//        // getting all the challenges
//    }

    private User findUserByEmail(UserDTO userDTO){
        try {
            Optional<User> user = userRepository.findUserByEmail(userDTO.getUserEmail());
            return user.orElse(null);
        }
        catch(Exception e){
            throw new GeneralException("UserService - finUserByEmail");
        }
    }
}
