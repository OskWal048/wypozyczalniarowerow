package pl.ioprojekt.wypozyczalniarowerow.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "repairs")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repair_id")
    private int id;

    @Column(name = "bike_id")
    private int bikeId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column()
    private String description;

    @Column(name = "job_done")
    private boolean jobDone;

    public int getId() {
        return id;
    }

    public Repair setId(int id) {
        this.id = id;
        return this;
    }

    public int getBikeId() {
        return bikeId;
    }

    public Repair setBikeId(int bikeId) {
        this.bikeId = bikeId;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Repair setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Repair setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Repair setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isJobDone() {
        return jobDone;
    }

    public Repair setJobDone(boolean jobDone) {
        this.jobDone = jobDone;
        return this;
    }
}
