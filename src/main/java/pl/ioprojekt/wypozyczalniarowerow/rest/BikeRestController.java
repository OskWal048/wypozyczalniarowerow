package pl.ioprojekt.wypozyczalniarowerow.rest;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ioprojekt.wypozyczalniarowerow.entity.Bike;
import pl.ioprojekt.wypozyczalniarowerow.helperclasses.ColumnValues;
import pl.ioprojekt.wypozyczalniarowerow.service.BikeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/bikes")
public class BikeRestController {

    BikeService bikeService;

    @Autowired
    public BikeRestController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping()
    public List<Bike> findFiltered(@RequestParam(name = "brand", defaultValue = "%") String brand,
                                   @RequestParam(name = "color", defaultValue = "%") String color,
                                   @RequestParam(name = "type", defaultValue = "%") String type,
                                   @RequestParam(name = "date1") Optional<String> date1,
                                   @RequestParam(name = "date2") Optional<String> date2,
                                   @RequestParam(name = "forUser") Optional<String> username,
                                   @RequestParam(name = "forEmployee") Optional<String> forEmployee) {

        List<Bike> result;

        if (date1.isPresent() && date2.isPresent())
            result = bikeService.findFiltered(brand, color, type, LocalDate.parse(date1.get()), LocalDate.parse(date2.get()));
        else
            result = bikeService.findFiltered(brand, color, type);

        if (username.isPresent())
            result = bikeService.filterByUsername(result, username.get());

        if(!forEmployee.isPresent())
            result = bikeService.filterUnusable(result);

        return result;
    }

    @GetMapping("/columns")
    public ColumnValues findColumnValues() {

        ColumnValues columnValues = new ColumnValues();
        columnValues.setBrands(bikeService.findColumnValues("brand"));
        columnValues.setSizes(bikeService.findColumnValues("size"));
        columnValues.setColors(bikeService.findColumnValues("color"));
        columnValues.setTypes(bikeService.findColumnValues("type"));
        columnValues.setSubtypes(bikeService.findColumnValues("subtype"));
        columnValues.setSuspensions(bikeService.findColumnValues("suspension"));

        return columnValues;
    }

    @GetMapping("/{id}")
    public Bike findById(@PathVariable int id) {
        return bikeService.findById(id);
    }

    @GetMapping("/{id}/reservations")
    public List<Pair<LocalDate, LocalDate>> findBikeReservations(@PathVariable int id) {

        return bikeService.findReservations(id);
    }


    @PostMapping()
    public Bike addBike(@RequestBody Bike bike) {
        bike.setId(0);
        bikeService.save(bike);
        return bike;
    }

    @PutMapping()
    public Bike updateBike(@RequestBody Bike bike) {
        bikeService.save(bike);
        return bike;
    }

}
