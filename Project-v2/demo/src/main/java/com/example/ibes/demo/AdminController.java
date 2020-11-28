package com.example.ibes.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/manage")
    public void manageAdminPanel() {
        throw new VvsException(HttpStatus.UNAUTHORIZED, "You need to be logged in as an admin to access this feature");
    }

    @GetMapping("/deleteUser/{userId}")
    public void deleteUser() {
        throw new VvsException(HttpStatus.NOT_IMPLEMENTED, "Feature not yet implemented");
    }
}
