package pl.ioprojekt.wypozyczalniarowerow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ioprojekt.wypozyczalniarowerow.dao.UserInfoRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.UserInfo;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }


    @Override
    public UserInfo findInfoByUsername(String username) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByUsernameLike(username);

        UserInfo userInfo;

        if (optionalUserInfo.isPresent())
            userInfo = optionalUserInfo.get();
        else
            throw new RuntimeException("Could not find user info for user: " + username);

        return userInfo;
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }


}
