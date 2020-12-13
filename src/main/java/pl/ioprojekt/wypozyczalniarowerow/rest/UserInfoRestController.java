package pl.ioprojekt.wypozyczalniarowerow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ioprojekt.wypozyczalniarowerow.entity.UserInfo;
import pl.ioprojekt.wypozyczalniarowerow.service.UserInfoService;

@CrossOrigin
@RestController
@RequestMapping("/userinfo")
public class UserInfoRestController {

    private UserInfoService userInfoService;

    @Autowired
    public UserInfoRestController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/{username}")
    public UserInfo findUserInfo(@PathVariable String username) {
        return userInfoService.findInfoByUsername(username);
    }

    @PutMapping()
    public UserInfo updateUserInfo(@RequestBody UserInfo userInfo) {
        userInfoService.save(userInfo);
        return userInfo;
    }
}
