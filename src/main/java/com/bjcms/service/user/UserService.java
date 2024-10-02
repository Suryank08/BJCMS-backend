package com.bjcms.service.user;

import com.bjcms.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User addUser(User user);
    public void deleteUser(int id);

    public User updateUser(User user);

    public User findUser(int id);

    public List<User> getAllUser();

    public Optional<User> findByUserId(Integer id);

    public User authenticateUser(User credentials);
}
