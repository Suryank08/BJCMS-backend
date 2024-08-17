package com.bjcms.rest.user;
import com.bjcms.config.authentication.JwtService;
import com.bjcms.entity.user.Role;
import com.bjcms.entity.user.User;
import com.bjcms.responses.LoginResponse;
import com.bjcms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public UserRestController(UserService userService,JwtService jwtService) {
        this.userService = userService;
        this.jwtService=jwtService;    }

 
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody User user) {
       Set<Role> roles= user.getRoles();
        for(Role role :roles){
            System.out.println(role.getRoleName());
        }
        User registeredUser =  userService.addUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody User credentials){
        User authenticatedUser = userService.authenticateUser(credentials);
        System.out.println(authenticatedUser.toString());
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    };
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'STUDENT' ,'INSTRUCTOR')")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/current")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findUser(@PathVariable int userId) {
        return userService.findUser(userId);
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        System.out.println("User Deleted");
    }

}
