package com.bjcms.dao.coaching;

import com.bjcms.entity.coaching.CoachingAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachingAdminDao extends JpaRepository<CoachingAdmin,Integer> {
    Optional<CoachingAdmin> findByCoachingAdminEmail(String email);
}
