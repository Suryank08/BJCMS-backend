package com.bjcms.service.coaching;

import com.bjcms.dao.coaching.CoachingAdminDao;
import com.bjcms.dao.user.RoleDao;
import com.bjcms.dao.user.UserDao;
import com.bjcms.entity.coaching.CoachingAdmin;
import com.bjcms.entity.user.Role;
import com.bjcms.entity.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CoachingAdminServiceImpl implements CoachingAdminService {
    private UserDao userDao;
    private RoleDao roleDao;
    private CoachingAdminDao coachingAdminDao;

    @Autowired
    public CoachingAdminServiceImpl(UserDao userDao, RoleDao roleDao, CoachingAdminDao coachingAdminDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.coachingAdminDao = coachingAdminDao;
    }

@Transactional
    public CoachingAdmin addCoAdmin(String email) {
        try {
            User user = userDao.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Role coachingAdminRole = roleDao.findByRoleName("CO-ADMIN")
                    .orElseThrow(() -> new IllegalArgumentException("Role CO-ADMIN not found"));
            CoachingAdmin coachingAdmin;
            if (user.getRoles().stream().noneMatch(role ->
                    "STUDENT".equals(role.getRoleName()) ||
                            "ADMIN".equals(role.getRoleName()) ||
                            "CO-ADMIN".equals(role.getRoleName())||
                            "INSTRUCTOR".equals(role.getRoleName()))) {
                coachingAdmin = new CoachingAdmin();
                coachingAdmin.setCoachingAdminEmail(user.getEmail());
                coachingAdmin.setCoachingAdminName(user.getFirstName() + " " + user.getLastName());
                coachingAdmin.setMobileNumber(user.getMobileNumber());
                coachingAdminDao.save(coachingAdmin);
                user.getRoles().add(coachingAdminRole);
                userDao.save(user);
            } else if (user.getRoles().stream().anyMatch(role -> "CO-ADMIN".equals(role.getRoleName()))) {
                coachingAdmin = coachingAdminDao.findByCoachingAdminEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("CO-ADMIN not found with email: " + email));
            } else {
                throw new IllegalStateException("User does not have the required role.");

            }
            return coachingAdminDao.save(coachingAdmin);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
