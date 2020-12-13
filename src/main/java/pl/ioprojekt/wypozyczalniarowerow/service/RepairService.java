package pl.ioprojekt.wypozyczalniarowerow.service;

import pl.ioprojekt.wypozyczalniarowerow.entity.Repair;

import java.util.List;

public interface RepairService {
    List<Repair> findAll();

    List<Repair> findFiltered(boolean jobDone);

    Repair findById(int id);

    void save(Repair repair);
}
