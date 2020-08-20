package com.security.demo.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bypt-dev-laptop-9
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    
    @RequestMapping(value = "/getText", method = RequestMethod.GET)
    public String getText() {
        return "<h1>Hello from Admin</h1>";
    }
    
}
