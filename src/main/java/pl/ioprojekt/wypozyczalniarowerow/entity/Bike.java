package pl.ioprojekt.wypozyczalniarowerow.entity;

import org.javatuples.Pair;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bikes")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bike_id")
    private int id;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column()
    private String brand;

    @Column()
    private String model;

    @Column(name = "size")
    private String bikeSize;

    @Column()
    private String color;

    @Column()
    private String type;

    @Column()
    private String subtype;

    @Enumerated(EnumType.STRING)
    private TargetGender targetGender;

    @Column()
    private boolean electric;

    @Column()
    private String suspension;

    @Column(name = "price_day")
    private double priceDay;

    @Column()
    private String description;

    @Column(name = "under_maintenance")
    private boolean underMaintenance;

    @Column()
    private boolean usable;

    public Bike() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(double priceDay) {
        this.priceDay = priceDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBikeSize() {
        return bikeSize;
    }

    public void setBikeSize(String bikeSize) {
        this.bikeSize = bikeSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getSuspension() {
        return suspension;
    }

    public void setSuspension(String suspension) {
        this.suspension = suspension;
    }

    public TargetGender getTargetGender() {
        return targetGender;
    }

    public void setTargetGender(TargetGender targetGender) {
        this.targetGender = targetGender;
    }

    public boolean isElectric() {
        return electric;
    }

    public void setElectric(boolean electric) {
        this.electric = electric;
    }

}
