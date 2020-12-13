package pl.ioprojekt.wypozyczalniarowerow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ioprojekt.wypozyczalniarowerow.dao.EquipmentRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.Equipment;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment findById(int id) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(id);

        Equipment equipment;

        if (equipmentOptional.isPresent())
            equipment = equipmentOptional.get();
        else
            throw new RuntimeException("Could not find equipment with id: " + id);

        return equipment;
    }

    @Override
    public void save(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    @Override
    public Equipment findByReservationInfo(String type, String size) {
        Equipment result = equipmentRepository.findByTypeLikeAndEqSizeLike(type, size).get(0);
        return result;
    }
}
