package pl.ioprojekt.wypozyczalniarowerow.service;

import pl.ioprojekt.wypozyczalniarowerow.entity.UserInfo;


public interface UserInfoService {

    UserInfo findInfoByUsername(String username);

    void save(UserInfo userInfo);
}
