package com.organicbazar.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping("/admin")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Welcome to Admin Dashboard");
    }

    @GetMapping("/farmer")
    public ResponseEntity<String> farmerDashboard() {
        return ResponseEntity.ok("Welcome to Farmer Dashboard");
    }

    @GetMapping("/customer")
    public ResponseEntity<String> customerDashboard() {
        return ResponseEntity.ok("Welcome to Customer Dashboard");
    }
}
