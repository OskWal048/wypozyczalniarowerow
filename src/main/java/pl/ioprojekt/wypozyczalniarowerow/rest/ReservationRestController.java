package pl.ioprojekt.wypozyczalniarowerow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ioprojekt.wypozyczalniarowerow.entity.Equipment;
import pl.ioprojekt.wypozyczalniarowerow.entity.Reservation;
import pl.ioprojekt.wypozyczalniarowerow.helperclasses.EquipmentSizes;
import pl.ioprojekt.wypozyczalniarowerow.service.ReservationService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reservations")
public class ReservationRestController {

    ReservationService reservationService;

    @Autowired
    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping()
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public Reservation findById(@PathVariable int id) {
        return reservationService.findById(id);
    }

    @GetMapping("/byUser/{username}")
    public List<Reservation> findByUsername(@PathVariable String username) {
        return reservationService.findByUsername(username);
    }

    @PutMapping()
    public Reservation update(@RequestBody Reservation reservation) {
        reservationService.save(reservation);
        return reservation;
    }

    @PostMapping()
    public Reservation add(@RequestBody Reservation reservation) {
        reservation.setId(0);


        EquipmentSizes eqSizes = reservation.getEquipmentSizes();
        List<Equipment> equipment = new ArrayList<>();

        if (eqSizes != null) {
            if (eqSizes.helmetSize != null)
                equipment.add(reservationService.findEquipmentByReservationInfo("kask", eqSizes.helmetSize));
            if (eqSizes.gogglesSize != null)
                equipment.add(reservationService.findEquipmentByReservationInfo("gogle", "%"));
            if (eqSizes.padsSize != null)
                equipment.add(reservationService.findEquipmentByReservationInfo("ochraniacze", eqSizes.padsSize));
            if (eqSizes.glovesSize != null)
                equipment.add(reservationService.findEquipmentByReservationInfo("rękawiczki", eqSizes.glovesSize));
            if (eqSizes.armorSize != null)
                equipment.add(reservationService.findEquipmentByReservationInfo("zbroja", eqSizes.getArmorSize()));
        }


        reservation.setEquipmentList(equipment);

        reservationService.save(reservation);
        return reservation;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        reservationService.deleteById(id);
    }


}
