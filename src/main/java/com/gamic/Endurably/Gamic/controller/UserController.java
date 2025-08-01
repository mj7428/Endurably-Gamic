package com.gamic.Endurably.Gamic.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamic.Endurably.Gamic.Entity.BaseLayout;
import com.gamic.Endurably.Gamic.Entity.Users;
import com.gamic.Endurably.Gamic.dto.UserResponseDto;
import com.gamic.Endurably.Gamic.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAll(){ 
        List<Users> userList = userService.getAllUsers();

        return userList.stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getAvatar()
                ))
                .collect(Collectors.toList());
    }

    @PostMapping
    public boolean createUser(@Valid @RequestBody Users userModal){
        return userService.addUsers(userModal);
    }

    @PostMapping("/{userId}/base")
    public String createnewBase( @PathVariable Long userId, @Valid @RequestBody BaseLayout base) {
        userService.createNewBaseForUser(userId, base);
        return "Base Added Successfully";
    }
    
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long userId) {
    userService.deleteUserById(userId);
        return "User with ID " + userId + " has been deleted by an admin.";
    }
    
}
