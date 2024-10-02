package com.bjcms.service.user;

import com.bjcms.dao.user.RoleDao;
import com.bjcms.dao.user.UserDao;
import com.bjcms.entity.user.Role;
import com.bjcms.entity.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        User user = new User();
        user.setFirstName(credentials.getFirstName());
        user.setLastName(credentials.getLastName());
        user.setEmail(credentials.getEmail());
        user.setMobileNumber(credentials.getMobileNumber());
        user.setPassword(passwordEncoder.encode(credentials.getPassword()));
        Set<String> roleNames = credentials.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        // Handling roles
        Set<Role> roles = credentials.getRoles().stream().map(role -> {
            Optional<Role> existingRole = roleDao.findByRoleName(role.getRoleName());
            return existingRole.orElseGet(() -> roleDao.save(role));
        }).collect(Collectors.toSet());

        user.setRoles(roles);

        return userDao.save(user);
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
                return userDao.findByEmail(credentials.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + credentials.getEmail()));
       }catch(AuthenticationException e) {
           throw new RuntimeException("Invalid credentials");
       }
    }



    @Transactional
    public User updateUser(User user) {
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
}
