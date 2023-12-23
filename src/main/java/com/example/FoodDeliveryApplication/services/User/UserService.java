package com.example.FoodDeliveryApplication.services.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.FoodDeliveryApplication.entities.Enums.Role;
import com.example.FoodDeliveryApplication.entities.User.User;
import com.example.FoodDeliveryApplication.entities.globals.LoginDetails;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.repository.User.UserRepository;
import com.example.FoodDeliveryApplication.repository.globals.LoginDetailsRepository;



@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginDetailsRepository loginDetailsRepository;

    public User createUser(User user)
    {
        user.setValid(true);
        userRepository.save(user);
        //LoginDetails loginDetails = new LoginDetails(user.getMail(), user.getPassword(), Role.USER);
        //loginDetailsRepository.save(loginDetails);
        return user;
    }

    public List<User> getAllUsers()
    {
        return (ArrayList<User>) userRepository.findAll();
    }

    public User getUser(int id)
    {
        return userRepository.findById(id).orElseThrow(()-> new EntityDoesNotExistException("wrong data"));
    }

    public User updateUser(User user)
    {
        User oldUser = getUser(user.getUserId());
        oldUser.setMail(user.getMail());
        oldUser.setName(user.getName());
        oldUser.setPassword(user.getPassword());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(oldUser);
        //why cant we save the new user directly when both old user and new user have their primary keys equal?
        return oldUser;
    }
    
    public boolean changeProfilePicture(int userId, MultipartFile file)
    {
        User oldUser = getUser(userId);
        try {
            oldUser.setImage(file.getBytes());
            userRepository.save(oldUser);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean blockUser(int userId)
    {
        User user = getUser(userId);
        user.setValid(false);
        userRepository.save(user);
        //cant update query for user be written directly?
        return true;
    }
    
    public boolean unblockUser(int userId)
    {
        User user = getUser(userId);
        user.setValid(true);
        userRepository.save(user);
        //cant update query for user be written directly?
        return true;
    }

    public InputStreamResource getProfilePicture(int userId)
    {
        byte[] bytes = getUser(userId).getImage();
        InputStream stream = new ByteArrayInputStream(bytes);
        InputStreamResource resource = new InputStreamResource(stream);
        return resource;
    }
}
