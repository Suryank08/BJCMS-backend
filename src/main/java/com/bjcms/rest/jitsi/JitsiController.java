package com.bjcms.rest.jitsi;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jitsi")
public class JitsiController {

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/createRoom")
    public ResponseEntity<String> createRoom() {
        // Create a room and return its name as a JSON object
        return ResponseEntity.ok("{\"roomName\":\"hellojdnj\"}"); // Return a JSON string
    }
}
