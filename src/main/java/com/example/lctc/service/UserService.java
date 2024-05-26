package com.example.lctc.service;

import com.example.lctc.dto.UserDTO;
import com.example.lctc.entity.User;
import com.example.lctc.exception.EmptyInputException;
import com.example.lctc.exception.GeneralException;
import com.example.lctc.exception.InvalidEmailException;
import com.example.lctc.repository.UserRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserDetails(Long userId){
        try {
            return userRepository.findById(userId).get();
        }
        catch (Exception e){
            throw new GeneralException("UserService - getUserDetails");
        }
    }

    public User addUser(UserDTO userDTO) {
        try{
            if(userDTO.getUserName().isEmpty()){
                throw new EmptyInputException("Please enter name");
            }
            else if (userDTO.getUserName().length() <= 2){
                throw new EmptyInputException("Please enter full name");
            }
            else if(userDTO.getUserEmail().isEmpty()){
                throw new EmptyInputException("Please enter email");
            }
            else if(!userDTO.getUserEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
                throw new InvalidEmailException("Please enter correct email");
            }
            else if(findUserByEmail(userDTO) != null){
                throw new InvalidEmailException("Email already registered");
            }
            else if(userDTO.getUserPass().isEmpty()){
                throw new EmptyInputException("Please enter password");
            }
            else if(userDTO.getUserPass().length() <= 4){
                throw new EmptyInputException("Password should be more that 4 characters");
            }

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
            User user = new ModelMapper()
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

    public User upcomingChallenges(UserDTO userDTO){
        // getting all the upcoming challenges
    }

    public User pastChallenges(UserDTO userDTO){
        // getting all the past challenges
    }

    // Internal usage

    public User challenges(UserDTO userDTO){
        // getting all the challenges
    }

    public User

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
