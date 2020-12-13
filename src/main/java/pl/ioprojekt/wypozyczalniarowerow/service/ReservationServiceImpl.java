package pl.ioprojekt.wypozyczalniarowerow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ioprojekt.wypozyczalniarowerow.dao.ReservationRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.Equipment;
import pl.ioprojekt.wypozyczalniarowerow.entity.Reservation;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository;

    EquipmentService equipmentService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, EquipmentService equipmentService) {
        this.reservationRepository = reservationRepository;
        this.equipmentService = equipmentService;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(int id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        Reservation reservation;

        if (optionalReservation.isPresent())
            reservation = optionalReservation.get();
        else
            throw new RuntimeException("Could not find a reservation with id: " + id);

        return reservation;
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findByBikeId(int id) {
        return reservationRepository.findAllByBike_Id(id);
    }

    @Override
    public Equipment findEquipmentByReservationInfo(String type, String size) {
        return equipmentService.findByReservationInfo(type, size);
    }

    @Override
    public List<Reservation> findByUsername(String username) {
        return reservationRepository.findAllByUsernameLike(username);
    }

    @Override
    public void deleteById(int id) {
        reservationRepository.deleteById(id);
    }
}
