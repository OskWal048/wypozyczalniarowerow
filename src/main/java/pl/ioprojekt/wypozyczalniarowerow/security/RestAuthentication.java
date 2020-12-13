package pl.ioprojekt.wypozyczalniarowerow.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
public class RestAuthentication {


    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
