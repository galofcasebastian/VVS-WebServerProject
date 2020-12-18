package com.example.ibes.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public void manageAdminPanel() {
        throw new VvsException(HttpStatus.UNAUTHORIZED, "You need to be logged in as an admin to access this feature");
    }

    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
    public void deleteUser() {
        throw new VvsException(HttpStatus.NOT_IMPLEMENTED, "Feature not yet implemented");
    }
}
