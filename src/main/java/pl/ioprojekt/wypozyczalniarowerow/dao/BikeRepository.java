package pl.ioprojekt.wypozyczalniarowerow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.ioprojekt.wypozyczalniarowerow.entity.Bike;

import java.util.List;

public interface BikeRepository extends JpaRepository<Bike, Integer> {

    @Query(value = "select distinct brand from Bike")
    public List<String> returnBrands();

    @Query(value = "select distinct bikeSize from Bike")
    public List<String> returnSizes();

    @Query(value = "select distinct color from Bike")
    public List<String> returnColors();

    @Query(value = "select distinct type from Bike")
    public List<String> returnTypes();

    @Query(value = "select distinct subtype from Bike")
    public List<String> returnSubtypes();

    @Query(value = "select distinct suspension from Bike")
    public List<String> returnSuspensions();

    public List<Bike> findByBrandLikeAndColorLikeAndTypeLike(String brand, String color, String type);

}
