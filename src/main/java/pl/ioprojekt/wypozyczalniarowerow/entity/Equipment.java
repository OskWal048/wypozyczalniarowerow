package pl.ioprojekt.wypozyczalniarowerow.entity;

import javax.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eq_id")
    private int id;

    @Column(name = "eq_type")
    private String type;

    @Column(name = "eq_size")
    private String eqSize;

    @Column(name = "price_day")
    private double priceDay;

    @Column()
    private boolean usable;

    public Equipment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEqSize() {
        return eqSize;
    }

    public void setEqSize(String eqSize) {
        this.eqSize = eqSize;
    }

    public double getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(double priceDay) {
        this.priceDay = priceDay;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }
}
