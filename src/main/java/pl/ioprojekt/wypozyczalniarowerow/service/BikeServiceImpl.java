package pl.ioprojekt.wypozyczalniarowerow.service;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ioprojekt.wypozyczalniarowerow.dao.BikeRepository;
import pl.ioprojekt.wypozyczalniarowerow.entity.Bike;
import pl.ioprojekt.wypozyczalniarowerow.entity.Reservation;
import pl.ioprojekt.wypozyczalniarowerow.entity.UserInfo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BikeServiceImpl implements BikeService {

    BikeRepository bikeRepository;

    ReservationService reservationService;

    UserInfoService userInfoService;

    @Autowired
    public BikeServiceImpl(BikeRepository bikeRepository, ReservationService reservationService, UserInfoService userInfoService) {
        this.bikeRepository = bikeRepository;
        this.reservationService = reservationService;
        this.userInfoService = userInfoService;
    }


    @Override
    public List<Bike> findAll() {
        return bikeRepository.findAll();
    }

    @Override
    public Bike findById(int id) {

        Optional<Bike> bikeOptional = bikeRepository.findById(id);

        Bike bike;

        if (bikeOptional.isPresent())
            bike = bikeOptional.get();
        else
            throw new RuntimeException("Could not find a bike with id: " + id);

        return bike;
    }

    @Override
    public void save(Bike bike) {

        bikeRepository.save(bike);
    }

    @Override
    public List<String> findColumnValues(String columnName) {

        switch (columnName) {
            case "brand":
                return bikeRepository.returnBrands();

            case "size":
                return bikeRepository.returnSizes();

            case "color":
                return bikeRepository.returnColors();

            case "type":
                return bikeRepository.returnTypes();

            case "subtype":
                return bikeRepository.returnSubtypes();

            case "suspension":
                return bikeRepository.returnSuspensions();
        }

        List<String> def = new ArrayList<String>();
        return def;
    }

    @Override
    public List<Bike> findFiltered(String brand, String color, String type) {
        List<Bike> result = bikeRepository.findByBrandLikeAndColorLikeAndTypeLike(brand, color, type);

        return result;
    }

    @Override
    public List<Bike> filterUnusable(List<Bike> list){
        return list.stream().filter(b -> !b.isUnderMaintenance() && b.isUsable()).collect(Collectors.toList());
    }

    @Override
    public List<Bike> findFiltered(String brand, String color, String type, LocalDate date1, LocalDate date2) {

        List<Bike> bikes = bikeRepository.findByBrandLikeAndColorLikeAndTypeLike(brand, color, type);

        for (int i = 0; i < bikes.size(); i++) {
            boolean isBikeWrong = false;

            List<Reservation> reservations = reservationService.findByBikeId(bikes.get(i).getId());
            for (Reservation res : reservations) {
                if ((res.getTimeFrom().isBefore(date1) && !res.getTimeTo().isBefore(date1)) ||
                        (res.getTimeFrom().isAfter(date1) && !res.getTimeFrom().isAfter(date2)) ||
                        (res.getTimeFrom().isEqual(date1)) || (res.getTimeFrom().isEqual(date1)) ||
                        (res.getTimeTo().isEqual(date1))) {
                    isBikeWrong = true;
                    break;
                }
            }

            if (isBikeWrong) {
                bikes.remove(i);
                i--;
            }
        }
        return bikes;
    }

    @Override
    public List<Pair<LocalDate, LocalDate>> findReservations(int id) {
        List<Pair<LocalDate, LocalDate>> result = new ArrayList<Pair<LocalDate, LocalDate>>();

        List<Reservation> reservations = reservationService.findByBikeId(id);

        for (Reservation res : reservations) {
            result.add(new Pair<LocalDate, LocalDate>(res.getTimeFrom(), res.getTimeTo()));
        }

        return result;
    }

    @Override
    public List<Bike> filterByUsername(List<Bike> list, String username) {
        UserInfo userInfo = userInfoService.findInfoByUsername(username);

        List<Bike> bikeList1 = list.stream().filter(b -> (b.getType().equals("miejski") || b.getType().equals("trekingowy")) && (b.getTargetGender().name().equals(userInfo.getGender().name()) || b.getTargetGender().name().equals("UNI") || !(userInfo.getGender().name().equals("MALE") || userInfo.getGender().name().equals("FEMALE")))).collect(Collectors.toList());
        List<Bike> bikeList2 = list.stream().filter(b -> (b.getType().equals("górski") || b.getType().equals("fatbike")) && (b.getTargetGender().name().equals(userInfo.getGender().name()) || b.getTargetGender().name().equals("UNI") || !(userInfo.getGender().name().equals("MALE") || userInfo.getGender().name().equals("FEMALE")))).collect(Collectors.toList());
        List<Bike> bikeList3 = list.stream().filter(b -> (b.getType().equals("szosowy")) && (b.getTargetGender().name().equals(userInfo.getGender().name()) || b.getTargetGender().name().equals("UNI") || !(userInfo.getGender().name().equals("MALE") || userInfo.getGender().name().equals("FEMALE")))).collect(Collectors.toList());
        int height = userInfo.getHeight();

        //<editor-fold desc="height for bikelist1">
        if (height < 162)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("17\"")).collect(Collectors.toList());
        else if (height == 162)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("17\"") || b.getBikeSize().equals("18\"")).collect(Collectors.toList());
        else if (height > 162 && height < 167)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("18\"")).collect(Collectors.toList());
        else if (height == 167)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("18\"") || b.getBikeSize().equals("19\"")).collect(Collectors.toList());
        else if (height > 167 && height < 172)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("19\"")).collect(Collectors.toList());
        else if (height == 172)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("19\"") || b.getBikeSize().equals("20\"")).collect(Collectors.toList());
        else if (height > 172 && height < 175)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("20\"")).collect(Collectors.toList());
        else if (height == 175)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("20\"") || b.getBikeSize().equals("21\"")).collect(Collectors.toList());
        else if (height > 175 && height < 177)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("21\"")).collect(Collectors.toList());
        else if (height == 177)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("21\"") || b.getBikeSize().equals("22\"")).collect(Collectors.toList());
        else if (height > 177 && height < 181)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("22\"")).collect(Collectors.toList());
        else if (height == 181)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("22\"") || b.getBikeSize().equals("23\"")).collect(Collectors.toList());
        else if (height > 181)
            bikeList1 = bikeList1.stream().filter(b -> b.getBikeSize().equals("23\"")).collect(Collectors.toList());
        //</editor-fold>

        //<editor-fold desc="height for bikelist2">
        if (height < 157)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("14\"")).collect(Collectors.toList());
        else if (height == 157)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("14\"") || b.getBikeSize().equals("15\"")).collect(Collectors.toList());
        else if (height > 157 && height < 162)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("15\"")).collect(Collectors.toList());
        else if (height == 162)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("15\"") || b.getBikeSize().equals("16\"")).collect(Collectors.toList());
        else if (height > 162 && height < 167)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("16\"")).collect(Collectors.toList());
        else if (height == 167)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("16\"") || b.getBikeSize().equals("17\"")).collect(Collectors.toList());
        else if (height > 167 && height < 174)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("17\"")).collect(Collectors.toList());
        else if (height == 174)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("17\"") || b.getBikeSize().equals("18\"")).collect(Collectors.toList());
        else if (height > 174 && height < 179)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("18\"")).collect(Collectors.toList());
        else if (height == 179)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("18\"") || b.getBikeSize().equals("19\"")).collect(Collectors.toList());
        else if (height > 179 && height < 185)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("19\"")).collect(Collectors.toList());
        else if (height == 185)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("19\"") || b.getBikeSize().equals("20\"")).collect(Collectors.toList());
        else if (height > 185 && height < 190)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("20\"")).collect(Collectors.toList());
        else if (height == 190)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("20\"") || b.getBikeSize().equals("21\"")).collect(Collectors.toList());
        else if (height > 190 && height < 195)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("21\"")).collect(Collectors.toList());
        else if (height == 195)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("21\"") || b.getBikeSize().equals("22\"")).collect(Collectors.toList());
        else if (height > 195)
            bikeList2 = bikeList2.stream().filter(b -> b.getBikeSize().equals("22\"")).collect(Collectors.toList());
        //</editor-fold>

        //<editor-fold desc="height for bikelist3">
        if (height < 158)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("47")).collect(Collectors.toList());
        else if (height == 158)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("47") || b.getBikeSize().equals("50")).collect(Collectors.toList());
        else if (height > 158 && height < 163)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("50")).collect(Collectors.toList());
        else if (height == 163)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("50") || b.getBikeSize().equals("52")).collect(Collectors.toList());
        else if (height > 163 && height < 168)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("52")).collect(Collectors.toList());
        else if (height == 168)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("52") || b.getBikeSize().equals("54")).collect(Collectors.toList());
        else if (height > 168 && height < 174)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("54")).collect(Collectors.toList());
        else if (height == 174)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("54") || b.getBikeSize().equals("56")).collect(Collectors.toList());
        else if (height > 174 && height < 180)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("56")).collect(Collectors.toList());
        else if (height == 180)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("56") || b.getBikeSize().equals("58")).collect(Collectors.toList());
        else if (height > 180 && height < 185)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("58")).collect(Collectors.toList());
        else if (height == 185)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("58") || b.getBikeSize().equals("60")).collect(Collectors.toList());
        else if (height > 185 && height < 190)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("60")).collect(Collectors.toList());
        else if (height == 190)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("60") || b.getBikeSize().equals("62")).collect(Collectors.toList());
        else if (height > 190)
            bikeList3 = bikeList3.stream().filter(b -> b.getBikeSize().equals("62")).collect(Collectors.toList());
        //</editor-fold>

        bikeList1.addAll(bikeList2);
        bikeList1.addAll(bikeList3);

        return bikeList1;
    }

}
