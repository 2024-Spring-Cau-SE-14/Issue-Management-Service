package com.se14.service.implement1;

import com.se14.domain.User;
import com.se14.repository.UserRepository;
import com.se14.service.UserService;

public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        if(user == null)
        {
            System.out.println("no user found");
        }else
        if(!user.getPassword().equals(password)) {
            System.out.println("Wrong password");
        }
        return null;
    }

    @Override
    public void addNewUser(String username, String password, String email) {
        User newUser = new User(password);
        newUser.setUsername(username);
        newUser.setEmail(email);
        userRepository.save(newUser);
    }
}
