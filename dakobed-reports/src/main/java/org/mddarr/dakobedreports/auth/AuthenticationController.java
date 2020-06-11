package org.mddarr.dakobedreports.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="auth")
@CrossOrigin
public class AuthenticationController {

    @PostMapping(value = "register")
    public String postReport(){
        return "yes";
    }
}
