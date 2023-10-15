package com.example.Python1.service;

import com.example.Python1.entity.User;
import com.example.Python1.kafka.KafkaProducer;
import com.example.Python1.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

   @Autowired
   UserRepository userRepository;

   @Autowired
    KafkaProducer kafkaProducer;

   public User saveUser(User user) throws ParseException {
       return userRepository.save(user);
   }

   public User registerUser(User user) throws ParseException{
       userRepository.save(user);
       LOG.info("Registering user -->"+user.getUserId());

       kafkaProducer.writeMessage(user.getUserEmail());
       LOG.info("Sending email for registration --> "+user.getUserEmail());
       return user;
   }

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public void deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            User deletedUser = user.get();
            userRepository.delete(deletedUser);
        }
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public void updateUser(String id,User UserForUpdate) {

        userRepository.findById(id).ifPresentOrElse(user -> {
                    user.setUserName(UserForUpdate.getUserName());
                    user.setUserEmail(UserForUpdate.getUserEmail());
                    userRepository.save(user);
                }, () -> {
                    throw new RuntimeException("No Record With this Id!");
                }
        );

    }
}
