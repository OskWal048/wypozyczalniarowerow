package pl.ioprojekt.wypozyczalniarowerow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.Equipment;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    List<Equipment> findByTypeLikeAndEqSizeLike(String type, String eqSize);
}
