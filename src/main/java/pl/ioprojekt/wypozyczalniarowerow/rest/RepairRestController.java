package pl.ioprojekt.wypozyczalniarowerow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ioprojekt.wypozyczalniarowerow.entity.Repair;
import pl.ioprojekt.wypozyczalniarowerow.service.RepairService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/repairs")
@CrossOrigin
public class RepairRestController {

    private RepairService repairService;

    @Autowired
    public RepairRestController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping()
    public List<Repair> findFiltered(@RequestParam(name = "finished") Optional<String> jobDone) {
        if (jobDone.isPresent())
            return repairService.findFiltered(Boolean.parseBoolean(jobDone.get()));
        else
            return repairService.findAll();
    }

    @GetMapping("/{id}")
    public Repair findById(@PathVariable int id) {
        return repairService.findById(id);
    }

    @PostMapping()
    public Repair add(@RequestBody Repair repair) {
        repair.setId(0);
        repairService.save(repair);
        return repair;
    }

    @PutMapping()
    public Repair update(@RequestBody Repair repair) {
        repairService.save(repair);
        return repair;
    }

}
