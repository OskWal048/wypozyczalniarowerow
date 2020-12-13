package pl.ioprojekt.wypozyczalniarowerow.service;

import org.springframework.stereotype.Service;
import pl.ioprojekt.wypozyczalniarowerow.dao.UserRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.User;

import java.util.List;

@Service
public class MyUserService {

    private final UserRepository userRepository;

    public MyUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }
}
