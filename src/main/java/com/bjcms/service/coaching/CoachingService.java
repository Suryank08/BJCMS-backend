package com.bjcms.service.coaching;

import com.bjcms.entity.coaching.Coaching;

public interface CoachingService {
    public Coaching findCoachingByCoachingName(String coachingName);
    public Coaching addCoaching(String email,Coaching coaching);
    public Coaching getCoachingOfCoachingAdmin(String email);
}
