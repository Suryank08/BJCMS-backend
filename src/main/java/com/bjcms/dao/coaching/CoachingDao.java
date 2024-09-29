package com.bjcms.dao.coaching;

import com.bjcms.entity.coaching.Coaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CoachingDao extends JpaRepository<Coaching, Integer> {
    Coaching findByCoachingName(String coachingName);
}

