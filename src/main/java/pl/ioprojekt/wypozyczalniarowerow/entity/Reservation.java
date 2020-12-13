package pl.ioprojekt.wypozyczalniarowerow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.ioprojekt.wypozyczalniarowerow.helperclasses.EquipmentSizes;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int id;

    @Column(name = "username")
    private String username;

    @ManyToOne()
    @JoinColumn(name = "bike_id")
    private Bike bike;

    @Column(name = "time_from")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate timeFrom;

    @Column(name = "time_to")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate timeTo;

    @Column(name = "is_premium")
    private boolean isPremium;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "pickup_address")
    private String pickupAddress;

    @Column()
    private boolean given;

    @Column()
    private boolean complete;

    @Column()
    private boolean cancelled;

    @Transient
    private EquipmentSizes equipmentSizes;

    @ManyToMany()
    @JoinTable(name = "reservation_equipment", joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "eq_id"))
    private List<Equipment> equipmentList;


    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Reservation setUsername(String username) {
        this.username = username;
        return this;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public LocalDate getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalDate timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDate getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalDate timeTo) {
        this.timeTo = timeTo;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public boolean isGiven() {
        return given;
    }

    public void setGiven(boolean given) {
        this.given = given;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public EquipmentSizes getEquipmentSizes() {
        return equipmentSizes;
    }

    public void setEquipmentSizes(EquipmentSizes equipmentSizes) {
        this.equipmentSizes = equipmentSizes;
    }
}
