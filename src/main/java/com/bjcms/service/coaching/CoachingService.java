package com.bjcms.service.coaching;

import com.bjcms.dto.coaching.CoachingDto;
import com.bjcms.entity.coaching.Coaching;

public interface CoachingService {
    public Coaching findCoachingByCoachingName(String coachingName);
    public Coaching findCoachingByCoachingId(Integer coachingId);
    public Coaching addCoaching(String email,Coaching coaching);
    public CoachingDto getCoachingOfCoachingAdmin(String email);
}
