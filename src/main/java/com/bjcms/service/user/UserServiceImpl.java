package com.bjcms.service.user;

import com.bjcms.dao.user.RoleDao;
import com.bjcms.dao.user.UserDao;
import com.bjcms.entity.user.Role;
import com.bjcms.entity.user.User;
import com.bjcms.responses.UserRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao,RoleDao roleDao, @Lazy AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleDao=roleDao;
    }

    @Transactional
    public User addUser(User credentials) {
        String email= credentials.getEmail();
        Optional<User> optionalUser = userDao.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new IllegalArgumentException("Email is already Registered with Our Platform");
        }else {
            Role userRole = roleDao.findByRoleName("USER")
                    .orElseThrow(() -> new IllegalArgumentException("Role USER not found"));
            User user = new User();
            user.setFirstName(credentials.getFirstName());
            user.setLastName(credentials.getLastName());
            user.setEmail(email);
            user.setMobileNumber(credentials.getMobileNumber());
            user.setPassword(passwordEncoder.encode(credentials.getPassword()));
            if (user.getRoles() == null) {
                user.setRoles(new HashSet<>());  // Initialize with an empty HashSet or other Set implementation
            }
            user.getRoles().add(userRole);
            return userDao.save(user);
        }
    }


    @Transactional
    public User authenticateUser(User credentials) {
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           credentials.getEmail(),
                           credentials.getPassword()
                   )
           );
                return findUserByEmail(credentials.getEmail());
       }catch(AuthenticationException e) {
           throw new RuntimeException("Invalid credentials");
       }
    }



    @Transactional
    public User updateUser(UserRequest userRequest, String email) {
        User user=findUserByEmail(email);
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setMobileNumber(userRequest.getMobileNumber());
        return userDao.save(user);
    }


    @Transactional
    public void deleteUser(int id) {
        Optional<User> optionalUser = userDao.findById(id);
        User user = optionalUser.orElse(new User());
        userDao.delete(user);
    }

    public List<User> getAllUser() {
        List<User> userList = userDao.findAll();
        return userList;
    }


    public User findUser(int id) {
        Optional<User> optionalUser = userDao.findById(id);
        User user = optionalUser.orElse(new User());
        return user;
    }


    public Optional<User> findByUserId(Integer id) {
        return userDao.findById(id);
    }

    public User findUserByEmail(String email) {
        User user=userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return user;
    }
}
