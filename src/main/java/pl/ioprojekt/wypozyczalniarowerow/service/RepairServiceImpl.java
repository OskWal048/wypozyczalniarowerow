package pl.ioprojekt.wypozyczalniarowerow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ioprojekt.wypozyczalniarowerow.dao.RepairRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.Repair;

import java.util.List;
import java.util.Optional;

@Service
public class RepairServiceImpl implements RepairService {

    RepairRepository repairRepository;

    @Autowired
    public RepairServiceImpl(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    @Override
    public List<Repair> findAll() {
        return repairRepository.findAll();
    }

    @Override
    public List<Repair> findFiltered(boolean jobDone) {
        return repairRepository.findAllByJobDoneIs(jobDone);
    }

    @Override
    public Repair findById(int id) {

        Optional<Repair> optionalRepair = repairRepository.findById(id);

        Repair repair = new Repair();

        if (optionalRepair.isPresent())
            repair = optionalRepair.get();
        else
            throw new RuntimeException("Could not find the repair with id: " + id);

        return repair;
    }

    @Override
    public void save(Repair repair) {
        repairRepository.save(repair);
    }
}
