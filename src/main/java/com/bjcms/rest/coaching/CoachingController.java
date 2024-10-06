package com.bjcms.rest.coaching;


import com.bjcms.dto.coaching.CoachingDto;
import com.bjcms.entity.coaching.Coaching;
import com.bjcms.service.coaching.CoachingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/coaching")
public class CoachingController {

    private static final Logger log = LoggerFactory.getLogger(CoachingController.class);
    @Autowired
    private CoachingService coachingService;

    @GetMapping("/{coachingName}")
    public ResponseEntity<Coaching> getCoachingByName(@PathVariable String coachingName) {
        log.debug("msg:- "+coachingName);
        if (coachingName != null && !coachingName.isEmpty()) {
            // Replace dashes with spaces if needed
            String formattedCoachingName = coachingName.replace("-", " ");
            Coaching coaching = coachingService.findCoachingByCoachingName(formattedCoachingName);
            if (coaching != null) {
                return ResponseEntity.ok(coaching);
            } else {
                return ResponseEntity.notFound().build(); // Return 404 if coaching not found
            }
        } else {
            log.debug("Not Found");
            // Handle the case where coachingName is not provided (default behavior)
            return ResponseEntity.badRequest().body(null); // or return a default response
        }
    }
    //TODO Later make a coaching Dto to send custom rather using @jsonIgnore in entity bean
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN','CO-ADMIN')")
    public ResponseEntity<Coaching> createCoaching(@RequestBody Coaching coachingRequest, Principal principal){
        String email=principal.getName();
        Coaching coaching=coachingService.addCoaching(email,coachingRequest);
        if (coaching != null) {
            return ResponseEntity.ok(coaching);
        } else {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('CO-ADMIN')")
    public ResponseEntity<CoachingDto> getCoachingOfCoachingAdmin(Principal principal){
        String email=principal.getName();
        CoachingDto coachingDto=coachingService.getCoachingOfCoachingAdmin(email);
        if (coachingDto != null) {
             return ResponseEntity.ok(coachingDto);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
