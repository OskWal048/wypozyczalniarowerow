package pl.ioprojekt.wypozyczalniarowerow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> findByUsernameLike(String username);
}
