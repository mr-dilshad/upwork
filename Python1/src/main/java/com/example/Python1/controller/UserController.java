package com.example.Python1.controller;

import com.example.Python1.entity.User;
import com.example.Python1.error.ResourceNotFoundException;
import com.example.Python1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        LOG.info("Getting all users");
        return userService.allUsers();
    }

    @PostMapping("/users/save")
    public User addUser(@RequestBody User user) throws ParseException {
        LOG.info("Saving User");
        userService.saveUser(user);
        LOG.info("Saved User : " + user);
        return user;
    }

    @PostMapping("/users/register")
    public User registerUser(@RequestBody User user) throws ParseException {
        LOG.info("Registering User");
        userService.registerUser(user);
        LOG.info("Saved User : " + user);
        return user;
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable("id") String userId){
        LOG.info("Finding By User Id");
        User user = userService.findUserById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found... by Id : " +userId));
        LOG.info("Found By User Id : " + userId);
        return user;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value="id") String userId, @RequestBody User userDetails){
        LOG.info("Updating By User Id");
        userService.updateUser(userId,userDetails);
        LOG.info("Updated By User Id : " + userId);
        return userDetails;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(value="id") String userId) {
        LOG.info("Deleting By User Id");
        userService.deleteUser(userId);
        LOG.info("Deleted By User Id : " + userId);
    }

}
