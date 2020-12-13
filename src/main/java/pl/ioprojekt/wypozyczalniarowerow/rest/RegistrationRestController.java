package pl.ioprojekt.wypozyczalniarowerow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import pl.ioprojekt.wypozyczalniarowerow.entity.UserInfo;
import pl.ioprojekt.wypozyczalniarowerow.helperclasses.User;
import pl.ioprojekt.wypozyczalniarowerow.service.UserInfoService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/registration")
public class RegistrationRestController {

    private UserDetailsManager userDetailsManager;
    private final UserInfoService userInfoService;

    @Autowired
    public RegistrationRestController(@Qualifier("jdbcUserDetailsManager") UserDetailsManager userDetailsManager, UserInfoService userInfoService) {
        this.userDetailsManager = userDetailsManager;
        this.userInfoService = userInfoService;
    }

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping()
    public void registerNewUser(@RequestBody User user) {

        String username = user.getUsername();

        String password = passwordEncoder.encode(user.getPassword());

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_CLIENT");

        org.springframework.security.core.userdetails.User tempUser = new org.springframework.security.core.userdetails.User(username, password, authorities);

        userDetailsManager.createUser(tempUser);

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(user.getUsername())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPhone(user.getPhone())
                .setEmail(user.getEmail())
                .setDateOfBirth(user.getDateOfBirth())
                .setHeight(user.getHeight())
                .setWeight(user.getWeight())
                .setGender(user.getGender());

        userInfoService.save(userInfo);
    }

    @PostMapping("/employee")
    public void registerNewEmployee(@RequestBody User user) {
        String username = user.getUsername();

        String password = passwordEncoder.encode(user.getPassword());

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");

        org.springframework.security.core.userdetails.User tempUser = new org.springframework.security.core.userdetails.User(username, password, authorities);

        userDetailsManager.createUser(tempUser);

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(user.getUsername())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPhone(user.getPhone())
                .setEmail(user.getEmail())
                .setDateOfBirth(user.getDateOfBirth())
                .setGender(user.getGender())
                .setPay(user.getPay());

        userInfoService.save(userInfo);
    }

    @PostMapping("/admin")
    public void registerNewAdmin(@RequestBody User user) {
        String username = user.getUsername();

        String password = passwordEncoder.encode(user.getPassword());

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");

        org.springframework.security.core.userdetails.User tempUser = new org.springframework.security.core.userdetails.User(username, password, authorities);

        userDetailsManager.createUser(tempUser);
    }

}
