package com.bjcms.rest.coaching;

import com.bjcms.entity.coaching.CoachingAdmin;
import com.bjcms.service.coaching.CoachingAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/coaching/admin")
public class CoachingAdminController {

    private CoachingAdminService coachingAdminService;
    @Autowired
    public CoachingAdminController(CoachingAdminService coachingAdminService) {
        this.coachingAdminService = coachingAdminService;
    }
//TODO update this api in later to register into specific plan that were created by admin by receiving plan id from cliet side
    @PostMapping("/create")
    public CoachingAdmin addCoAdmin(Principal principal) {
        String email = principal.getName();
        return coachingAdminService.addCoAdmin(email);
    }

}
