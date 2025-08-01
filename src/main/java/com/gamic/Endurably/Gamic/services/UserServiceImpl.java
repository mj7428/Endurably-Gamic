package com.gamic.Endurably.Gamic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gamic.Endurably.Gamic.Entity.BaseLayout;
import com.gamic.Endurably.Gamic.Entity.Users;
import com.gamic.Endurably.Gamic.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean addUsers(Users user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already in use.");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createNewBaseForUser(Long userId, BaseLayout newLayout) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found with ID: " + userId));

        user.addBaseLayout(newLayout);
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        user.setEnabled(false);
        userRepository.save(user);
    }
}
