package com.bjcms.dao.user;

import com.bjcms.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
//
//    @Query("SELECT user FROM User user WHERE user.email = :email AND user.password = :password")
//    public User authenticateUser(@Param("email") String email, @Param("password") String password);

    Optional<User> findByEmail(String email);
}
