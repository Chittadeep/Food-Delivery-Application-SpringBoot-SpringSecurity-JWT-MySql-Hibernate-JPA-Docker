package com.example.FoodDeliveryApplication.controllers.User;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.FoodDeliveryApplication.entities.User.User;
import com.example.FoodDeliveryApplication.model.request.UserRequest;
import com.example.FoodDeliveryApplication.services.User.UserService;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping(path = "/admin/user")
    ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/admins")
    public ResponseEntity<List<User>> getAllAdmins() {
        return new ResponseEntity<List<User>>(userService.getAllAdmins(), HttpStatus.OK);
    }
    

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id)
    {
        return new ResponseEntity<User>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping(path="/user")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest)
    {
        return new ResponseEntity<User>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PutMapping(path="/user")
    public ResponseEntity<User> updateUser(@RequestBody User user)
    {
        return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.ACCEPTED);
    }

    @PatchMapping(path="/admin/user/block/{id}")
    public ResponseEntity<Boolean> blockUser(@PathVariable int id)
    {
        return new ResponseEntity<Boolean>(userService.blockUser(id), HttpStatus.ACCEPTED);
    }

    @PatchMapping(path="/admin/user/unblock/{id}")
    public ResponseEntity<Boolean> unblockUser(@PathVariable int id)
    {
        return new ResponseEntity<Boolean>(userService.unblockUser(id), HttpStatus.ACCEPTED);
    }

    @PatchMapping(path="/user/{userId}/changeProfilePicture")
    public ResponseEntity<Boolean> changeProfilePicture(@PathVariable int userId, @RequestParam("file") MultipartFile file)
    {
        return new ResponseEntity<Boolean>(userService.changeProfilePicture(userId, file), HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/user/{userId}/getProfilePicture")
    public ResponseEntity<InputStreamResource> getProfilePicture(@PathVariable int userId)
    {    
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=userProfilePic.jpg");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.IMAGE_JPEG).body(userService.getProfilePicture(userId));
    }
    
    @PatchMapping(path = "/user/{userId}/setUserAsAdmin")
    public ResponseEntity<Boolean> setUserAsAdmin(@PathVariable int userId)
    {
        return new ResponseEntity<Boolean>(userService.updateUserToAdmin(userId), HttpStatus.OK);
    }

    @PatchMapping(path = "/user/{userId}/removeUserAsAdmin")
    public ResponseEntity<Boolean> removeUserAsAdmin(@PathVariable int userId)
    {
        return new ResponseEntity<Boolean>(userService.removeUserAsAdmin(userId), HttpStatus.OK);
    }

    @PatchMapping(path = "/user/updatePassword")
    public ResponseEntity<Boolean> updateUserPassword(@RequestParam int userId, @RequestParam String password)
    {
        return new ResponseEntity<Boolean>(userService.updatePassword(userId, password), HttpStatus.OK);
    }

    @PatchMapping(path = "/user/updateMail")
    public ResponseEntity<Boolean> updateUserMail(@RequestParam int userId, @RequestParam String mail)
    {
        return new ResponseEntity<Boolean>(userService.updateMail(userId, mail), HttpStatus.OK);
    }

}
