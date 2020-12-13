package pl.ioprojekt.wypozyczalniarowerow.rest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ioprojekt.wypozyczalniarowerow.entity.User;
import pl.ioprojekt.wypozyczalniarowerow.service.MyUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/employees")
public class EmployeesRestController {

    private final MyUserService myUserService;

    public EmployeesRestController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping()
    public List<UserDetails> findAll() {

        List<User> users = myUserService.findAll();

        users = users.stream().filter(u -> u.hasAuthority("ROLE_EMPLOYEE")).collect(Collectors.toList());

        List<UserDetails> results = new ArrayList<>();

        for (User u : users)
            results.add(u);

        return results;
    }

}
