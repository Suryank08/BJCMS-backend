package com.bjcms.rest.user;

import com.bjcms.config.authentication.JwtService;
import com.bjcms.entity.user.User;
import com.bjcms.responses.LoginResponse;
import com.bjcms.responses.UserRequest;
import com.bjcms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public UserRestController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }
//TODO for now use try and catch block later replace it with global exception handeling

   @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            User registeredUser = userService.addUser(user);
            return ResponseEntity.ok("User Saved Sucessfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody User credentials) {
        User authenticatedUser = userService.authenticateUser(credentials);
        System.out.println(authenticatedUser.toString());
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh-token")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT','USER')")
    public ResponseEntity<LoginResponse> refreshToken(@RequestHeader("Authorization") String token, Principal principal) {
        // Check if the token starts with "Bearer "
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Extract the actual token by removing the "Bearer " prefix
        String jwtToken = token.substring(7);

        // Get the username from the Principal
        String username = principal.getName();
        User user = userService.findUserByEmail(username);

        // Validate the existing token
        if (jwtService.validateToken(jwtToken, user.getUsername())) {
            // Generate a new token for the user
            String newToken = jwtService.generateToken(user);

            // Prepare the login response
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(newToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());

            return ResponseEntity.ok(loginResponse);
        } else {
            // If the token is invalid, return an unauthorized response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER','CO-ADMIN' ,'STUDENT' ,'INSTRUCTOR')")
    public  ResponseEntity<User> updateUser(@RequestBody UserRequest userRequest, Principal principal) {
        String email=principal.getName();
       User user= userService.updateUser(userRequest,email);
       return ResponseEntity.ok(user);
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
