package pl.ioprojekt.wypozyczalniarowerow.service;

import pl.ioprojekt.wypozyczalniarowerow.entity.Equipment;
import pl.ioprojekt.wypozyczalniarowerow.entity.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAll();

    Reservation findById(int id);

    void save(Reservation reservation);

    List<Reservation> findByBikeId(int id);

    Equipment findEquipmentByReservationInfo(String type, String size);

    List<Reservation> findByUsername(String username);

    void deleteById(int id);
}
