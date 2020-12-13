package pl.ioprojekt.wypozyczalniarowerow.dao;

import pl.ioprojekt.wypozyczalniarowerow.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Integer> {
    List<Repair> findAllByJobDoneIs(boolean jobDone);
}
