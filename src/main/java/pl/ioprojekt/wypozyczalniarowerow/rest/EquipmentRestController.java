package pl.ioprojekt.wypozyczalniarowerow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ioprojekt.wypozyczalniarowerow.entity.Equipment;
import pl.ioprojekt.wypozyczalniarowerow.service.EquipmentService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/equipment")
public class EquipmentRestController {

    EquipmentService equipmentService;

    @Autowired
    public EquipmentRestController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping()
    public List<Equipment> findAll() {
        return equipmentService.findAll();
    }

    @GetMapping("/{id}")
    public Equipment findById(@PathVariable int id) {
        return equipmentService.findById(id);
    }

    @PutMapping()
    public Equipment updateEquipment(@RequestBody Equipment equipment) {
        equipmentService.save(equipment);
        return equipment;
    }

    @PostMapping()
    public Equipment addEquipment(@RequestBody Equipment equipment) {
        equipment.setId(0);
        equipmentService.save(equipment);
        return equipment;
    }


}
