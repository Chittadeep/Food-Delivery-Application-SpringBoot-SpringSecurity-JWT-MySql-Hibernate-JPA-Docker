package com.example.FoodDeliveryApplication.services.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.FoodDeliveryApplication.entities.Enums.Role;
import com.example.FoodDeliveryApplication.entities.User.User;
import com.example.FoodDeliveryApplication.entities.globals.LoginDetails;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.exceptions.ImageRequestedDoesNotExistException;
import com.example.FoodDeliveryApplication.model.request.UserRequest;
import com.example.FoodDeliveryApplication.repository.User.UserRepository;
import com.example.FoodDeliveryApplication.repository.globals.LoginDetailsRepository;



@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginDetailsRepository loginDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserRequest userRequest)
    {
        User user = new User(userRequest);
        user.setValid(true);
        userRepository.save(user);
        String passwordEncoded = passwordEncoder.encode(userRequest.getPassword());
        LoginDetails loginDetails = new LoginDetails(user.getMail(), passwordEncoded, Role.USER);
        loginDetailsRepository.save(loginDetails);
        return user;
    }

    public List<User> getAllUsers()
    {
        return (ArrayList<User>) userRepository.findAll();
    }

    public List<User> getAllAdmins()
    {
        return (ArrayList<User>) userRepository.getAllAdmins();
    }

    public User getUser(int id)
    {
        validateUserAndAdmin(id);
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("wrong data"));
    }

    public User updateUser(User user)
    {   
        validateUser(user.getUserId());     
        User oldUser = getUser(user.getUserId());
        oldUser.setName(user.getName());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(oldUser);
        //why cant we save the new user directly when both old user and new user have their primary keys equal?
        return oldUser;
    }
    
    public boolean changeProfilePicture(int userId, MultipartFile file)
    {
        validateUser(userId);
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
        validateUserAndAdmin(userId);
        byte[] bytes = getUser(userId).getImage();
        if(bytes==null) throw new RuntimeException("The image you requested does not exist");
        InputStream stream = new ByteArrayInputStream(bytes);
        InputStreamResource resource = new InputStreamResource(stream);
        return resource;
    }

    public boolean updateUserToAdmin(int userId)
    {
        User oldUser = getUser(userId);
        oldUser.setAdmin(true);
        userRepository.save(oldUser);
        LoginDetails loginDetails =loginDetailsRepository.getLoginDetailsByUserName(oldUser.getMail());
        loginDetails.setRole(Role.ADMIN);
        loginDetailsRepository.save(loginDetails);
        return true;
    }

    public boolean removeUserAsAdmin(int userId)
    {
        User oldUser = getUser(userId);
        oldUser.setAdmin(false);
        userRepository.save(oldUser);
        LoginDetails loginDetails =loginDetailsRepository.getLoginDetailsByUserName(oldUser.getMail());
        loginDetails.setRole(Role.USER);
        loginDetailsRepository.save(loginDetails);
        return true;
    }

    public boolean updatePassword(int userId, String password)
    {
        validateUser(userId);
        User user = getUser(userId);
        LoginDetails oldLoginDetails = loginDetailsRepository.getLoginDetailsByUserName(user.getMail());
        oldLoginDetails.setPassword(passwordEncoder.encode(password));
        loginDetailsRepository.save(oldLoginDetails);
        return true;
    }

    public boolean updateMail(int userId, String mail)
    {
        validateUser(userId);
        User user = getUser(userId);
        LoginDetails oldLoginDetails = loginDetailsRepository.getLoginDetailsByUserName(user.getMail());
        oldLoginDetails.setUserName(mail);
        user.setMail(mail);
        loginDetailsRepository.save(oldLoginDetails);
        userRepository.save(user);
        return true;
    }

    //for endpoints which can be triggered by any admin but only user having the userId and its related JWTToken
    private void validateUserAndAdmin(int userId)
    {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.getUserByMail(userDetail.getUsername());
        if(loggedInUser.isAdmin()) return;
        if(loggedInUser.getUserId()!=userId) 
        {
            throw new RuntimeException("This user cannot access/update other users details");
        }
    }

    private void validateUser(int userId)
    {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.getUserByMail(userDetail.getUsername());
        if(loggedInUser.getUserId()!=userId) 
        {
            throw new RuntimeException("This user cannot access/update other users details");
        }
    }
}
