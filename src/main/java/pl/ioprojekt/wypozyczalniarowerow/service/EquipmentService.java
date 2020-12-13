package pl.ioprojekt.wypozyczalniarowerow.service;

import pl.ioprojekt.wypozyczalniarowerow.entity.Equipment;

import java.util.List;

public interface EquipmentService {

    List<Equipment> findAll();

    Equipment findById(int id);

    void save(Equipment equipment);

    Equipment findByReservationInfo(String type, String size);
}
