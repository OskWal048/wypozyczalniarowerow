package pl.ioprojekt.wypozyczalniarowerow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

}
