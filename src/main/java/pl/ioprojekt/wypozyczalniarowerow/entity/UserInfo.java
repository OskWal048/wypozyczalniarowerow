package pl.ioprojekt.wypozyczalniarowerow.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int id;

    @Column()
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column()
    private String phone;

    @Column()
    private String email;

    @Column()
    private LocalDate dateOfBirth;

    @Column()
    private int height;

    @Column()
    private double weight;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column()
    private double pay;

    public UserInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserInfo setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserInfo setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserInfo setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserInfo setHeight(int height) {
        this.height = height;
        return this;
    }

    public UserInfo setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public UserInfo setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public UserInfo setPay(double pay) {
        this.pay = pay;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public Gender getGender() {
        return gender;
    }

    public double getPay() {
        return pay;
    }
}
