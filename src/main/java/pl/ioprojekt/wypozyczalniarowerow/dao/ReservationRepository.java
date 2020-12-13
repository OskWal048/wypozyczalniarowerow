package pl.ioprojekt.wypozyczalniarowerow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByUsernameLike(String username);

    List<Reservation> findAllByBike_Id(int id);
}
