package com.gamic.Endurably.Gamic.services;

import java.util.List;

import com.gamic.Endurably.Gamic.Entity.BaseLayout;
import com.gamic.Endurably.Gamic.Entity.Users;

public interface UserService {
    public boolean addUsers(Users user);
    public List<Users> getAllUsers();
    public void createNewBaseForUser(Long userId, BaseLayout newLayout);
    public void deleteUserById(Long userId);
}
