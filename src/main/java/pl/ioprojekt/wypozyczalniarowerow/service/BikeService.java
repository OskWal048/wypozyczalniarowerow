package pl.ioprojekt.wypozyczalniarowerow.service;

import org.javatuples.Pair;
import pl.ioprojekt.wypozyczalniarowerow.entity.Bike;


import java.time.LocalDate;
import java.util.List;

public interface BikeService {

    List<Bike> findAll();

    Bike findById(int id);

    void save(Bike bike);

    List<String> findColumnValues(String columnName);

    List<Bike> findFiltered(String brand, String color, String type);

    List<Bike> findFiltered(String brand, String color, String type, LocalDate date1, LocalDate date2);

    List<Pair<LocalDate, LocalDate>> findReservations(int id);

    List<Bike> filterByUsername(List<Bike> list, String username);

    List<Bike> filterUnusable(List<Bike> list);
}
